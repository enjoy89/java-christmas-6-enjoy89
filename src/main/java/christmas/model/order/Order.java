package christmas.model.order;

import christmas.common.DiscountEventName;
import christmas.model.discount.Amount;
import christmas.model.discount.DiscountEventManager;
import christmas.model.discount.GiftEvent;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final DiscountEventManager discountEventManager;
    private final GiftEvent giftEvent;

    public Order(OrderDate orderDate, OrderInformation orderInformation) {
        this.discountEventManager = new DiscountEventManager(orderDate, orderInformation);
        this.giftEvent = GiftEvent.of(orderInformation.getTotalAmount());
    }

    public Map<String, Integer> getAppliedDiscountEvents() {
        Amount giftEventBenefit = getGiftEventBenefit();
        discountEventManager.applyDiscount();
        Map<String, Integer> appliedDiscountEvents = new HashMap<>();

        if (getGiftEventBenefit().amount() != 0) {
            appliedDiscountEvents.put(DiscountEventName.GIFT_EVENT.get(), giftEventBenefit.amount());
        }

        appliedDiscountEvents.putAll(discountEventManager.getAppliedDiscountEvents());
        return appliedDiscountEvents;
    }

    public Amount getTotalBenefit() {
        int totalBenefit = 0;

        Amount totalDiscount = discountEventManager.getTotalDiscountAmount();
        Amount giftEventBenefit = getGiftEventBenefit();
        totalBenefit = totalDiscount.amount() + giftEventBenefit.amount();

        return new Amount(totalBenefit);
    }

    public Amount getTotalBenefitExceptGiftBenefit() {
        return new Amount(calculateTotalBenefitExceptGiftBenefit());
    }

    private int calculateTotalBenefitExceptGiftBenefit() {
        return getTotalBenefit().amount() - getGiftEventBenefit().amount();
    }

    public Amount getGiftEventBenefit() {
        return giftEvent.getBenefit();
    }
}
