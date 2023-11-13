package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.model.order.Date;
import christmas.model.order.OrderDate;

public class SpecialDiscountEvent implements DiscountEvent {
    private final OrderDate date;

    public SpecialDiscountEvent(OrderDate date) {
        this.date = date;
    }

    public static SpecialDiscountEvent of(OrderDate date) {
        return new SpecialDiscountEvent(date);
    }

    @Override
    public Amount calculateTotalDiscountAmount() {
        int discount = 0;
        if (isPossibleDate(date.getDate())) {
            discount = (int) Constant.INITIAL_DISCOUNT_AMOUNT.getValue();
        }
        return new Amount(discount);
    }

    private boolean isPossibleDate(Date date) {
        return date.isSpecialDay();
    }

    @Override
    public String getName() {
        return DiscountEventName.SPECIAL_EVENT.get();
    }
}
