package christmas.controller;

import christmas.model.discount.Amount;
import christmas.model.discount.Badge;
import christmas.model.discount.Benefit;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    public void start() {
        printStartInstructions();

        OrderDate orderDate = getOrderDate();
        OrderInformation orderInformation = getOrderInformation();

        Order order = createOrder(orderDate, orderInformation);
        Amount totalAmount = orderInformation.getTotalAmount();
        printTotalAmount(totalAmount);

        Amount totalBenefit = applyDiscount(order);
        printTotalBenefitAmount(totalBenefit);

        Amount totalBenefitExceptGiftBenefit = order.getTotalBenefitExceptGiftBenefit();
        OutputView.printAmountAfterDiscount(getEstimatedPaymentAmount(totalAmount, totalBenefitExceptGiftBenefit));
        printBadge(totalBenefit);
    }

    private void printGiftEvent(Order order) {
        OutputView.printGiftMenu(order.getGiftEventBenefit());
    }

    private void printStartInstructions() {
        OutputView.printStartInstructions();
    }

    private OrderDate getOrderDate() {
        try {
            return OrderDate.of(InputView.inputVisitDate());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getOrderDate();
        }
    }

    private OrderInformation getOrderInformation() {
        try {
            return OrderInformation.of(InputView.inputOrderInformation());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getOrderInformation();
        }
    }

    private Order createOrder(OrderDate orderDate, OrderInformation orderInformation) {
        printOrderMenus(orderDate, orderInformation);
        return new Order(orderDate, orderInformation);
    }

    private void printOrderMenus(OrderDate orderDate, OrderInformation orderInformation) {
        OutputView.printPreviewInstructions(orderDate.getDate());
        OutputView.printOrderMenus(orderInformation.getOrderItems());
    }

    private void printTotalAmount(Amount totalAmount) {
        OutputView.printTotalAmountBeforeDiscount(totalAmount);
    }

    private Amount applyDiscount(Order order) {
        printGiftEvent(order);
        OutputView.printBenefitsDetails(order.getAppliedDiscountEvents());
        return order.getTotalBenefit();
    }

    private void printTotalBenefitAmount(Amount totalBenefit) {
        OutputView.printTotalBenefitAmount(totalBenefit);
    }

    private Amount getEstimatedPaymentAmount(Amount totalAmount, Amount totalBenefitExceptGiftBenefit) {
        return new Amount(totalAmount.amount() - totalBenefitExceptGiftBenefit.amount());
    }

    private void printBadge(Amount totalBenefit) {
        Benefit benefit = Benefit.of(totalBenefit);
        OutputView.printEventBadge(benefit.getBadge());
    }
}
