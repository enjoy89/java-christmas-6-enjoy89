package christmas.model.discount;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.MenuCategory;

public class WeekendDiscountEvent implements DiscountEvent {

    private final Menu menu;

    private WeekendDiscountEvent(Date date, Menu menu) {
        if (!isPossibleEvent(date)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_DATE_WEEKEND_EVENT.get());
        }
        this.menu = menu;
    }

    public static WeekendDiscountEvent of(Date date, Menu menu) {
        return new WeekendDiscountEvent(date, menu);
    }

    @Override
    public boolean isPossibleEvent(Date date) {
        return isPossibleDate(date);
    }

    @Override
    public int calculateTotalDiscountAmount() {
        return calculateDiscountAmount(menu);
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
