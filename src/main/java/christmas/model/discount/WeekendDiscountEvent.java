package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.MenuCategory;
import christmas.model.order.OrderItem;

public class WeekendDiscountEvent implements DiscountEvent {

    private final OrderItem orderItem;
    private boolean applied;

    private WeekendDiscountEvent(Date date, OrderItem orderItem) {

        if (!isPossibleEvent(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_WEEKEND_EVENT.get());
        }
        this.orderItem = orderItem;
        this.applied = false;
    }

    public static WeekendDiscountEvent of(Date date, OrderItem orderItem) {
        return new WeekendDiscountEvent(date, orderItem);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        applied = true;
        return calculateDiscountAmount(orderItem.getMenuByOrderItem());
    }

    @Override
    public boolean isApplied() {
        return applied;
    }

    @Override
    public String getName() {
        return DiscountEventName.WEEKEND_EVENT.get();
    }

    private int calculateDiscountAmount(Menu menu) {
        if (isMainMenu(menu)) {
            return menu.getPrice() - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue();
        }
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekend(date.getDay());
    }

    private boolean isMainMenu(Menu menu) {
        return menu.getCategory() == MenuCategory.MAIN;
    }

}
