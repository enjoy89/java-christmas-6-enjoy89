package christmas.model.order;

import christmas.common.ErrorMessage;
import christmas.utils.InputValueValidator;
import java.util.HashMap;
import java.util.Map;

public class OrderParser {

    private final Map<String, Integer> orders;

    private OrderParser(String orderInfo) {
        this.orders = parseOrder(orderInfo);
    }

    public Map<String, Integer> getOrders() {
        return orders;
    }

    public static OrderParser of(String orderInfo) {
        return new OrderParser(orderInfo);
    }

    private Map<String, Integer> parseOrder(String orderInfo) {
        validateOrderFormat(orderInfo);
        Map<String, Integer> parseOrders = new HashMap<>();

        String[] orderItems = orderInfo.split(",");

        for (String orderItem : orderItems) {
            String[] parts = parseOrderItem(orderItem);

            String menuName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            parseOrders.put(menuName, quantity);
        }
        return parseOrders;
    }

    private static String[] parseOrderItem(String orderItem) {
        String[] parts = orderItem.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
        return parts;
    }

    private static void validateOrderFormat(String orderInfo) {
        if (!orderInfo.matches("([가-힣]+-\\d+)(,[가-힣]+-\\d+)*")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }

        if (InputValueValidator.isContainsBlank(orderInfo) || InputValueValidator.isEndsWithComma(orderInfo)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

}
