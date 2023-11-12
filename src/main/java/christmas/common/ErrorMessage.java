package christmas.common;

public enum ErrorMessage {
    /* 할인 이벤트 관련 */
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    IMPOSSIBLE_DATE_CHRISTMAS_EVENT("크리스마스 할인 이벤트 기간에 해당하지 않는 날짜입니다."),
    IMPOSSIBLE_DATE_WEEKDAY_EVENT("평일 할인 이벤트 기간에 해당하지 않는 날짜입니다."),
    IMPOSSIBLE_DATE_WEEKEND_EVENT("주말 할인 이벤트 기간에 해당하지 않는 날짜입니다."),
    IMPOSSIBLE_DATE_SPECIAL_EVENT("특별 할인 이벤트 기간에 해당하지 않는 날짜입니다."),
    IMPOSSIBLE_GIFT_EVENT("증정 할인 이벤트를 적용할 수 없는 조건입니다."),

    /* 주문 관련 */
    NULL_ORDER("[ERROR] 주문이 입력되지 않았습니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
