package christmas.model.order;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isInRange(int start, int end) {
        return day >= start && day <= end;
    }
}
