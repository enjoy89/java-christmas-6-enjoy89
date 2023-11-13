package christmas.model.order;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {
    private final int day;

    public Date(int day) {
        validateDay(day);
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    private void validateDay(int day) {
        if (day < (int) Constant.FIRST_DATE.getValue() || day > (int) Constant.LAST_DATE.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
    }

    public boolean isInRange(int start, int end) {
        return day >= start && day <= end;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;

    }

    public boolean isSpecialDay() {
        return isSunday(day) || isChristmasDay(day);
    }

    private boolean isSunday(int day) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isChristmasDay(int day) {
        return day == (int) Constant.CHRISTMAS_DATE.getValue();
    }

    private DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(2023, 12, day).getDayOfWeek();
    }
}
