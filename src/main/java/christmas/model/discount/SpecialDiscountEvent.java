package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;

public class SpecialDiscountEvent implements DiscountEvent {
    private boolean applied;

    private SpecialDiscountEvent(Date date) {
        if (!isPossibleDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_SPECIAL_EVENT.get());
        }
        this.applied = false;
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
        applied = true;
        return (int) Constant.INITIAL_DISCOUNT_AMOUNT.getValue();
    }

    @Override
    public boolean isApplied() {
        return applied;
    }

    private boolean isPossibleDate(Date date) {
        return date.isSpecialDay(date.getDay());
    }

    public String getDescription() {
        return DiscountEventName.SPECIAL_EVENT.get();
    }

}
