package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.model.order.Date;
import christmas.model.order.OrderDate;

public class ChristmasDiscountEvent implements DiscountEvent {
    private final OrderDate date;

    private ChristmasDiscountEvent(OrderDate date) {
        this.date = date;
    }

    public static ChristmasDiscountEvent of(OrderDate date) {
        return new ChristmasDiscountEvent(date);
    }

    @Override
    public Amount calculateTotalDiscountAmount() {
        if (isPossibleDate(date.getDate())) {
            int dateCount = calculateDateCount(date);
            int discount = (int) Constant.INITIAL_DISCOUNT_AMOUNT.getValue() + calculateDiscountAmount(dateCount);
            return new Amount(discount);
        }
        return new Amount(0);
    }

    @Override
    public String getName() {
        return DiscountEventName.CHRISTMAS_EVENT.get();
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange((int) Constant.FIRST_DATE.getValue(), (int) Constant.CHRISTMAS_DATE.getValue());
    }

    private int calculateDateCount(OrderDate date) {
        if (isFirstDate(date.getDate())) {
            return (int) Constant.FIRST_DATE.getValue();
        }
        return date.getDate().getDay() - (int) Constant.FIRST_DATE.getValue();
    }

    private int calculateDiscountAmount(int dateCount) {
        if (dateCount == 1) {
            return 0;
        }
        return dateCount * (int) Constant.DAILY_DISCOUNT_INCREASE.getValue();
    }

    private boolean isFirstDate(Date date) {
        return date.getDay() == (int) Constant.FIRST_DATE.getValue();
    }

}
