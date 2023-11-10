package christmas.model.order;

import christmas.common.ErrorMessage;

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
}
