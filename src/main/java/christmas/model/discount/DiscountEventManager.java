package christmas.model.discount;

import christmas.common.Constant;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountEventManager {
    private Map<String, Integer> appliedDiscountEvents;
    private List<DiscountEvent> discountEvents;
    private GiftEvent giftEvent;

    public DiscountEventManager(OrderDate orderDate, OrderInformation orderInformation) {
        this.discountEvents = new ArrayList<>();
        this.discountEvents.add(ChristmasDiscountEvent.of(orderDate));
        this.discountEvents.add(WeekdayDiscountEvent.of(orderDate, orderInformation));
        this.discountEvents.add(WeekendDiscountEvent.of(orderDate, orderInformation));
        this.discountEvents.add(SpecialDiscountEvent.of(orderDate));
        this.giftEvent = GiftEvent.of();
        this.appliedDiscountEvents = new HashMap<>();
    }

    public Amount applyDiscount() {
        int totalDiscount = 0;

        for (DiscountEvent discountEvent : discountEvents) {
            Amount discountAmount = discountEvent.calculateTotalDiscountAmount();
            if (discountAmount.amount() > 0) {
                appliedDiscountEvents.put(discountEvent.getName(), discountAmount.amount());
            }

            totalDiscount += discountAmount.amount();
        }

        Amount applyGiftEvent = applyGiftEvent(totalDiscount);
        totalDiscount += applyGiftEvent.amount();
        return new Amount(totalDiscount);
    }

    public Map<String, Integer> getAppliedDiscountEvents() {
        return appliedDiscountEvents;
    }

    private Amount applyGiftEvent(int totalDiscount) {
        int applyGiftEvent = 0;
        if (canApplyGiftEvent(totalDiscount)) {
            applyGiftEvent = giftEvent.calculateTotalDiscountAmount().amount();
        }
        appliedDiscountEvents.put(giftEvent.getName(), applyGiftEvent);
        return new Amount(applyGiftEvent);
    }

    private boolean canApplyGiftEvent(int totalDiscount) {
        return totalDiscount >= (int) Constant.GIFT_EVENT_AMOUNT.getValue();
    }

}
