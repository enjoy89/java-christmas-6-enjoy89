package christmas.controller;

import christmas.model.discount.Amount;
import christmas.model.order.Order;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    public void start() {

        printStartInstructions();

        OrderDate orderDate = getOrderDate(InputView.inputVisitDate());
        OrderInformation orderInformation = getOrderInformation(InputView.inputOrderInformation());

        Order order = createOrder(orderDate, orderInformation);

        //4. 할인 전 총주문 금액 출력
        Amount totalAmount = orderInformation.getTotalAmount();
        OutputView.printTotalAmountBeforeDiscount(totalAmount);

        applyDiscount(order);

        Amount totalBenefitExceptGiftBenefit = order.getTotalBenefitExceptGiftBenefit();
        OutputView.printAmountAfterDiscount(getEstimatedPaymentAmount(totalAmount, totalBenefitExceptGiftBenefit));
    }

    private Order createOrder(OrderDate orderDate, OrderInformation orderInformation) {
        // 3. 주문 메뉴를 출력한다.
        printOrderMenus(orderDate, orderInformation);
        return new Order(orderDate, orderInformation);
    }

    private void applyDiscount(Order order) {
        printGiftEvent(order);
        OutputView.printBenefitsDetails(order.getAppliedDiscountEvents());
        Amount totalBenefit = order.getTotalBenefit();
        OutputView.printTotalBenefitAmount(totalBenefit);
    }

    private void printGiftEvent(Order order) {
        OutputView.printGiftMenu(order.getGiftEventBenefit());
    }

    private Amount getEstimatedPaymentAmount(Amount totalAmount, Amount totalBenefitExceptGiftBenefit) {
        return new Amount(totalAmount.amount() - totalBenefitExceptGiftBenefit.amount());
    }

    private void printStartInstructions() {
        OutputView.printStartInstructions();
    }

    private OrderDate getOrderDate(String input) {
        return OrderDate.of(input);
    }

    private OrderInformation getOrderInformation(String input) {
        return OrderInformation.of(input);
    }

    private void printOrderMenus(OrderDate orderDate, OrderInformation orderInformation) {
        OutputView.printPreviewInstructions(orderDate.getDate());
        OutputView.printOrderMenus(orderInformation.getOrderItems());
    }
}
