package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.MenuCategory;

public class WeekdayDiscountEvent implements DiscountEvent {

    private final Menu menu;
    private boolean applied;

    private WeekdayDiscountEvent(Date date, Menu menu) {
        if (!isPossibleDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_WEEKDAY_EVENT.get());
        }
        this.menu = menu;
        this.applied = false;
    }

    public static WeekdayDiscountEvent of(Date date, Menu menu) {
        return new WeekdayDiscountEvent(date, menu);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        applied = true;
        return calculateDiscountAmount(menu);
    }

    @Override
    public boolean isApplied() {
        return applied;
    }

    private int calculateDiscountAmount(Menu menu) {
        if (isDessertMenu(menu)) {
            return menu.getPrice() - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue();
        }
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekday(date.getDay());
    }

    private boolean isDessertMenu(Menu menu) {
        return menu.getCategory() == MenuCategory.DESSERT;
    }

    public String getDescription() {
        return DiscountEventName.WEEKDAY_EVENT.get();
    }
}
