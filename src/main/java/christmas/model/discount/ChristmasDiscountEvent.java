package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;

public class ChristmasDiscountEvent implements DiscountEvent {

    private final Date date;

    private ChristmasDiscountEvent(Date date) {
        if (!isPossibleEvent(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_CHRISTMAS_EVENT.get());
        }
        this.date = date;
    }

    public static ChristmasDiscountEvent of(Date date) {
        return new ChristmasDiscountEvent(date);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        int dateCount = calculateDateCount(date);
        int discountAmount = calculateDiscountAmount(dateCount);
        return (int) Constant.INITIAL_DISCOUNT_AMOUNT.getValue() + discountAmount;
    }

    private int calculateDateCount(Date orderDate) {
        if (isFirstDate(orderDate)) {
            return (int) Constant.FIRST_DATE.getValue();
        }
        return orderDate.getDay() - (int) Constant.FIRST_DATE.getValue();
    }

    private int calculateDiscountAmount(int dateCount) {
        if (dateCount == 1) {
            return 0;
        }
        return dateCount * (int) Constant.DAILY_DISCOUNT_INCREASE.getValue();
    }

    private boolean isFirstDate(Date date) {
        return date.getDay() == 1;
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange((int) Constant.FIRST_DATE.getValue(), (int) Constant.CHRISTMAS_DATE.getValue());
    }

}
