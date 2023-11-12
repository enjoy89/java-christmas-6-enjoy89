package christmas.common;

public enum Constant {
    MIN_POSSIBLE_ORDER_AMOUNT(10_000),
    WEEKDAY_WEEKEND_DISCOUNT_AMOUNT(2023),
    INITIAL_DISCOUNT_AMOUNT(1000),
    DAILY_DISCOUNT_INCREASE(100),
    GIFT_EVENT_AMOUNT(120_000),
    FIRST_DATE(1),
    LAST_DATE(31),
    CHRISTMAS_DATE(25),
    BLANK(" "),
    COMMA(","),
    MAX_ORDER_QUANTITY(20),
    MIN_MENU_QUANTITY(1),
    ORDER_FORMAT_REGEX("([가-힣]+-\\d+)(,[가-힣]+-\\d+)*");

    private final Object value;

    Constant(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}

