package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.model.order.Menu;

public class GiftEvent {

    private final Amount totalAmount;

    private GiftEvent(Amount totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static GiftEvent of(Amount totalAmount) {
        return new GiftEvent(totalAmount);
    }

    public Amount getBenefit() {
        int benefit = 0;
        if (isPossible()) {
            benefit = getGiftMenu();
        }
        return new Amount(benefit);
    }

    private int getGiftMenu() {
        return Menu.DRINK_3.getPrice();
    }

    private boolean isPossible() {
        return totalAmount.amount() >= (int) Constant.GIFT_EVENT_AMOUNT.getValue();
    }
}
