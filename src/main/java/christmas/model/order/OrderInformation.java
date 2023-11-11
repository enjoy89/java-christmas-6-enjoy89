package christmas.model.order;

import christmas.model.discount.Amount;
import java.util.List;

public class OrderInformation {
    private final List<OrderItem> orderItems;
    private final Amount totalAmount;

    public OrderInformation(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.totalAmount = calculateTotalOrderAmount();
    }

    public static OrderInformation of(String orderInfo) {
        return new OrderInformation(OrderParser.parseOrder(orderInfo));
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Amount getTotalAmount() {
        return totalAmount;
    }

    private Amount calculateTotalOrderAmount() {
        int totalAmount = 0;

        for (OrderItem orderItem : orderItems) {
            totalAmount += orderItem.calculateTotalPrice();
        }
        return new Amount(totalAmount);
    }
}
