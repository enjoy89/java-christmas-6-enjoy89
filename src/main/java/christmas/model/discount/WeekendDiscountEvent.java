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

public class WeekendDiscountEvent implements DiscountEvent {
    private final OrderDate date;
    private final OrderInformation orderInformation;

    private WeekendDiscountEvent(OrderDate date, OrderInformation orderInformation) {
        this.date = date;
        this.orderInformation = orderInformation;
    }

    public static WeekendDiscountEvent of(OrderDate date, OrderInformation orderInformation) {
        return new WeekendDiscountEvent(date, orderInformation);
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
        if (isMainMenu(menu)) {
            return (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * quantity;
        }
        return 0;
    }

    private boolean isMainMenu(Menu menu) {
        return menu.getCategory() == MenuCategory.MAIN;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekend();
    }

    @Override
    public String getName() {
        return DiscountEventName.WEEKEND_EVENT.get();
    }

}
