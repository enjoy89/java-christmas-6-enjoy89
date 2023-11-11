package christmas.common;

public enum Constant {
    MIN_POSSIBLE_ORDER_AMOUNT(10_000),
    WEEKDAY_WEEKEND_DISCOUNT_AMOUNT(2023),
    INITIAL_DISCOUNT_AMOUNT(1000),
    DAILY_DISCOUNT_INCREASE(100),
    FIRST_DATE(1),
    CHRISTMAS_DATE(25);

    private final Object value;

    Constant(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}

