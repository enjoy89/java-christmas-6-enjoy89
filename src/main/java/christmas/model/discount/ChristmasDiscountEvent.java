package christmas.model.discount;

import christmas.model.order.Date;

public class ChristmasDiscountEvent implements DiscountEvent {

    private final Date date;

    public ChristmasDiscountEvent(Date date) {
        this.date = date;
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return date.isInRange(1, 25);
    }

    @Override
    public int calculateDiscount(Amount totalAmount) {
        return 0;
    }
}
