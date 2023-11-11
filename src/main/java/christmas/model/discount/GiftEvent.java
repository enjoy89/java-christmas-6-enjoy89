package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;

public class GiftEvent {

    private GiftEvent(Date date, Amount totalAmount) {
        if (!isPossibleEvent(date, totalAmount)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_GIFT_EVENT.get());
        }
    }

    public static GiftEvent of(Date date, Amount totalAmount) {
        return new GiftEvent(date, totalAmount);
    }

    public int calculateTotalAmount() {
        return getChampagne();
    }

    private boolean isPossibleEvent(Date date, Amount totalAmount) {
        return isPossibleDate(date) && isPossibleAmount(totalAmount);
    }


    private int getChampagne() {
        return Menu.DRINK_3.getPrice();
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange((int) Constant.FIRST_DATE.getValue(), (int) Constant.LAST_DATE.getValue());
    }

    private boolean isPossibleAmount(Amount totalAmount) {
        return totalAmount.getAmount() >= (int) Constant.GIFT_EVENT_AMOUNT.getValue();
    }

}
