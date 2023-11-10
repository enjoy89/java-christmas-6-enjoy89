package christmas.model.discount;

import christmas.model.order.Date;

public class ChristmasDiscountEvent implements DiscountEvent {
    private static final int MIN_POSSIBLE_ORDER_AMOUNT = 10_000;

    private final Date date;

    public ChristmasDiscountEvent(Date date) {
        this.date = date;
    }

    @Override
    public boolean isPossibleEvent(Date date, Amount totalAmount) {
        return isPossibleDate(date) && isPossibleAmount(totalAmount);
    }

    @Override
    public int calculateDiscount(Amount totalAmount) {
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isInRange(1, 25);
    }

    private boolean isPossibleAmount(Amount totalAmount) {
        return totalAmount.getAmount() >= MIN_POSSIBLE_ORDER_AMOUNT;
    }
}
