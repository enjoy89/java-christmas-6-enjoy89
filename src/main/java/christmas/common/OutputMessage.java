package christmas.common;

public enum OutputMessage {
    PRIZE_FORMAT("#,###원"),
    START_INSTRUCTIONS("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PREVIEW_INSTRUCTIONS_PREFIX("12월 "),
    PREVIEW_INSTRUCTIONS_POSTFIX("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENUS("<주문 메뉴>"),
    TOTAL_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFITS_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    AMOUNT_AFTER_DISCOUNT("할인 후 예상 결제 금액"),
    EVENT_BADGE("12d월 이벤트 배지");

    private String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
