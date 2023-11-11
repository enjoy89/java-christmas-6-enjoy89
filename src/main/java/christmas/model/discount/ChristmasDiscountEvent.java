package christmas.model.discount;

import christmas.common.ErrorMessage;
import christmas.model.order.Date;

public class ChristmasDiscountEvent implements DiscountEvent {

    private static final int INITIAL_DISCOUNT_AMOUNT = 1000;
    private static final int DAILY_DISCOUNT_INCREASE = 100;
    private static final int FIRST_DATE = 1;
    private static final int CHRISTMAS_DATE = 25;

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
        return INITIAL_DISCOUNT_AMOUNT + discountAmount;
    }

    private int calculateDateCount(Date orderDate) {
        if (isFirstDate(orderDate)) {
            return FIRST_DATE;
        }
        return orderDate.getDay() - FIRST_DATE;
    }

    private int calculateDiscountAmount(int dateCount) {
        if (dateCount == 1) {
            return 0;
        }
        return dateCount * DAILY_DISCOUNT_INCREASE;
    }

    private boolean isFirstDate(Date date) {
        return date.getDay() == 1;
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange(FIRST_DATE, CHRISTMAS_DATE);
    }

}
