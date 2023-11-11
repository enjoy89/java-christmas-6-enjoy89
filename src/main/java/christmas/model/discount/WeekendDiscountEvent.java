package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.DiscountEventName;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menus;
import christmas.model.order.MenuCategory;

public class WeekendDiscountEvent implements DiscountEvent {

    private final Menus menusList;

    private WeekendDiscountEvent(Date date, Menus menusList) {
        if (!isPossibleEvent(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_WEEKEND_EVENT.get());
        }
        this.menusList = menusList;
    }

    public static WeekendDiscountEvent of(Date date, Menus menusList) {
        return new WeekendDiscountEvent(date, menusList);
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
        if (isMainMenu(menusList)) {
            return menusList.getPrice() - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue();
        }
        return 0;
    }

    private boolean isPossibleDate(Date date) {
        return date.isWeekend(date.getDay());
    }

    private boolean isMainMenu(Menus menusList) {
        return menusList.getCategory() == MenuCategory.MAIN;
    }

    public String getDescription() {
        return DiscountEventName.WEEKEND_EVENT.get();
    }

}
