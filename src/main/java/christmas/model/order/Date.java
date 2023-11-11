package christmas.model.order;

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
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
    }

    public boolean isInRange(int start, int end) {
        return day >= start && day <= end;
    }

    public boolean isWeekday(int day) {
        return !isWeekend(day);
    }

    public boolean isWeekend(int day) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;

    }

    public boolean isSpecialDay(int day) {
        return isSunday(day) || isChristmasDay(day);
    }

    private boolean isSunday(int day) {
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        return dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isChristmasDay(int day) {
        return day == 25;
    }

    private DayOfWeek getDayOfWeek(int day) {
        return LocalDate.of(2023, 12, day).getDayOfWeek();
    }
}
