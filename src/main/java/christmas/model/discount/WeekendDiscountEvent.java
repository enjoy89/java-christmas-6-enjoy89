package christmas.model.discount;

import christmas.model.order.Date;

public class WeekendDiscountEvent implements DiscountEvent {

    private final Date date;

    public WeekendDiscountEvent(Date date) {
        this.date = date;
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateDiscount(Amount totalAmount) {
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange(1, 31);
    }
}
