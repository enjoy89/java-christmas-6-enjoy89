package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.MenuCategory;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import christmas.model.order.OrderItem;
import java.util.List;

public class WeekdayDiscountEvent implements DiscountEvent {
    private final OrderDate date;
    private final OrderInformation orderInformation;

    private WeekdayDiscountEvent(OrderDate date, OrderInformation orderInformation) {
        this.date = date;
        this.orderInformation = orderInformation;
    }

    public static WeekdayDiscountEvent of(OrderDate date, OrderInformation orderInformation) {
        return new WeekdayDiscountEvent(date, orderInformation);
    }

    @Override
    public Amount calculateTotalDiscountAmount() {
        int discount = 0;
        if (isPossibleDate(date.getDate())) {
            List<OrderItem> orderItems = orderInformation.getOrderItems();

            for (OrderItem orderItem : orderItems) {
                discount += calculateDiscountAmount(orderItem.getMenuByOrderItem(), orderItem.getQuantity());
            }
        }
        return new Amount(discount);
    }

    private int calculateDiscountAmount(Menu menu, int quantity) {
        if (isDessertMenu(menu)) {
            return (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * quantity;
        }
        return 0;
    }

    private boolean isDessertMenu(Menu menu) {
        return menu.getCategory() == MenuCategory.DESSERT;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekday();
    }

    @Override
    public String getName() {
        return DiscountEventName.WEEKDAY_EVENT.get();
    }

}
