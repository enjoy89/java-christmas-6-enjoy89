package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;

public class SpecialDiscountEvent implements DiscountEvent {
    private final Date date;

    private SpecialDiscountEvent(Date date) {
        if (!isPossibleDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_SPECIAL_EVENT.get());
        }
        this.date = date;
    }

    public static SpecialDiscountEvent of(Date date) {
        return new SpecialDiscountEvent(date);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        return (int) Constant.INITIAL_DISCOUNT_AMOUNT.getValue();
    }

    private boolean isPossibleDate(Date date) {
        return date.isSpecialDay(date.getDay());
    }

}
