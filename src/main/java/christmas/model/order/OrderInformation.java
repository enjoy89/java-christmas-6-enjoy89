package christmas.model.order;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.discount.Amount;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderInformation {
    private final List<OrderItem> orderItems;
    private final Amount totalAmount;

    private OrderInformation(String orderInfo) {
        this.orderItems = parseOrder(orderInfo);
        validateOrder();
        this.totalAmount = calculateTotalOrderAmount();
    }

    public static OrderInformation of(String orderInfo) {
        return new OrderInformation(orderInfo);
    }

    private List<OrderItem> parseOrder(String orderInfo) {
        return OrderParser.parseOrder(orderInfo);
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

    private void validateOrder() {
        validateDuplicate();
        validateTotalQuantity();
        validateIsOnlyDrink();
        validateMinQuantity();
    }

    private void validateTotalQuantity() {
        int totalQuantity = calculateTotalQuantity();

        if (totalQuantity >= (int) Constant.MAX_ORDER_QUANTITY.getValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

    private int calculateTotalQuantity() {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    private void validateIsOnlyDrink() {
        if (!containsNonDrink()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

    private boolean containsNonDrink() {
        return orderItems.stream()
                .anyMatch(item -> item.getCategory() != MenuCategory.DRINK);
    }

    private void validateMinQuantity() {
        if (!allItemsMinQuantity()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

    private void validateDuplicate() {
        if (hasDuplicateMenu()) {
            throw new IllegalArgumentException((ErrorMessage.INVALID_ORDER.get()));
        }
    }

    private boolean allItemsMinQuantity() {
        return orderItems.stream()
                .allMatch(item -> item.getQuantity() >= (int) Constant.MIN_MENU_QUANTITY.getValue());
    }

    private boolean hasDuplicateMenu() {
        Set<Menu> uniqueMenus = new HashSet<>();
        boolean hasDuplicate = false;

        for (OrderItem orderItem : orderItems) {
            if (!uniqueMenus.add(orderItem.getMenuByOrderItem())) {
                hasDuplicate = true;
            }
        }
        return hasDuplicate;
    }

}
