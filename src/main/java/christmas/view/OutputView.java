package christmas.view;

import christmas.common.Constant;
import christmas.common.OutputMessage;
import christmas.model.discount.Amount;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.OrderItem;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat PRIZE_FORMAT = new DecimalFormat(OutputMessage.PRIZE_FORMAT.get());

    public static void printStartInstructions() {
        System.out.println(OutputMessage.START_INSTRUCTIONS.get());
    }

    public static void printPreviewInstructions(Date date) {
        System.out.println(String.format("%s%s%s",
                OutputMessage.PREVIEW_INSTRUCTIONS_PREFIX.get(), date.getDay(),
                OutputMessage.PREVIEW_INSTRUCTIONS_POSTFIX.get()));
    }

    public static void printOrderMenus(List<OrderItem> orderItems) {
        System.out.println(Constant.LINE.getValue() + OutputMessage.ORDER_MENUS.get());
        orderItems.forEach(orderItem ->
                System.out.println(String.format("%s %d개", orderItem.getMenuName(), orderItem.getQuantity())));
    }

    public static void printTotalAmountBeforeDiscount(Amount totalAmount) {
        System.out.println(
                String.format(Constant.LINE.getValue() + "%s%n%s", OutputMessage.TOTAL_AMOUNT_BEFORE_DISCOUNT.get(),
                        PRIZE_FORMAT.format(totalAmount.amount())));
    }

    public static void printGiftMenu(Amount giftBenefit) {
        System.out.println(String.format(Constant.LINE.getValue() + "%s", OutputMessage.GIFT_MENU.get()));
        if (giftBenefit.amount() == 0) {
            System.out.println(Constant.NOTHING.getValue());
        }
        System.out.println(Menu.DRINK_3.getName());
    }

    public static void printBenefitsDetails(Map<String, Integer> appliedDiscountEvents) {
        System.out.println(String.format(Constant.LINE.getValue() + "%s", OutputMessage.BENEFITS_DETAILS.get()));
        if (appliedDiscountEvents.isEmpty()) {
            System.out.println(Constant.NOTHING.getValue());
        }

        appliedDiscountEvents.forEach((name, discount) ->
                System.out.println(String.format("%s: -%s", name, PRIZE_FORMAT.format(discount))));
    }

    public static void printTotalBenefitAmount(Amount totalBenefitAmount) {
        System.out.println(String.format(Constant.LINE.getValue() + "%s%n%s", OutputMessage.TOTAL_BENEFIT_AMOUNT.get(),
                PRIZE_FORMAT.format(totalBenefitAmount.amount())));
    }

    public static void printAmountAfterDiscount(Amount afterDiscount) {
        System.out.println(String.format(Constant.LINE.getValue() + "%s%n%s", OutputMessage.AMOUNT_AFTER_DISCOUNT.get(),
                PRIZE_FORMAT.format(afterDiscount.amount())));
    }

    public static void printEventBadge() {
        System.out.println(Constant.LINE.getValue() + OutputMessage.EVENT_BADGE.get());
    }
}
