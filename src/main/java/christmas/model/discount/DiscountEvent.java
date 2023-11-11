package christmas.model.discount;

import christmas.model.order.Date;

public interface DiscountEvent {

    boolean isPossibleEvent(Date date);

    int calculateTotalDiscountAmount();

}
