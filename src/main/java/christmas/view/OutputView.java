package christmas.view;

import christmas.common.OutputMessage;

public class OutputView {

    public static void printStartInstructions() {
        System.out.println(OutputMessage.START_INSTRUCTIONS);
    }

    public static void printPreviewInstructions(int date) {
        System.out.println(OutputMessage.PREVIEW_INSTRUCTIONS_PREFIX.getMessage()
                + date
                + OutputMessage.PREVIEW_INSTRUCTIONS_POSTFIX.getMessage());
    }

    public static void printOrderMenus() {
        System.out.println(OutputMessage.ORDER_MENUS);
    }

    public static void printTotalAmountBeforeDiscount() {
        System.out.println(OutputMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT);
    }

    public static void printGiftMenu() {
        System.out.println(OutputMessage.GIFT_MENU);
    }

    public static void printBenefitsDetails() {
        System.out.println(OutputMessage.BENEFITS_DETAILS);
    }

    public static void printTotalBenefitAmount() {
        System.out.println(OutputMessage.TOTAL_BENEFIT_AMOUNT);
    }

    public static void printAmountAfterDiscount() {
        System.out.println(OutputMessage.AMOUNT_AFTER_DISCOUNT);
    }

    public static void printEventBadge() {
        System.out.println(OutputMessage.EVENT_BADGE);
    }
}
