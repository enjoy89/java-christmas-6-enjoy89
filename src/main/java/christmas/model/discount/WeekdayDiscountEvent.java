package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menus;
import christmas.model.order.MenuCategory;

public class WeekdayDiscountEvent implements DiscountEvent {

    private final Menus menusList;

    private WeekdayDiscountEvent(Date date, Menus menusList) {
        if (!isPossibleDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_WEEKDAY_EVENT.get());
        }
        this.menusList = menusList;
    }

    public static WeekdayDiscountEvent of(Date date, Menus menusList) {
        return new WeekdayDiscountEvent(date, menusList);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        return calculateDiscountAmount(menusList);
    }

    private int calculateDiscountAmount(Menus menusList) {
        if (isDessertMenu(menusList)) {
            return menusList.getPrice() - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue();
        }
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekday(date.getDay());
    }

    private boolean isDessertMenu(Menus menusList) {
        return menusList.getCategory() == MenuCategory.DESSERT;
    }

    public String getDescription() {
        return DiscountEventName.WEEKDAY_EVENT.get();
    }
}
