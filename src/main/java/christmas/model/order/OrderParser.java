package christmas.model.order;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    public static List<OrderItem> parseOrder(String orderInfo) {
        validateOrderFormat(orderInfo);
        List<OrderItem> orderItems = new ArrayList<>();
        String[] orderParts = splitOrderInfo(orderInfo);

        for (String orderPart : orderParts) {
            String[] itemParts = splitOrderItem(orderPart);

            String menuName = itemParts[0];
            int quantity = Integer.parseInt(itemParts[1]);

            OrderItem orderItem = new OrderItem(menuName, quantity);
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private static String[] splitOrderInfo(String orderInfo) {
        return orderInfo.split((String) Constant.COMMA.getValue());
    }

    private static String[] splitOrderItem(String orderItem) {
        String[] parts = orderItem.split((String) Constant.BAR.getValue());
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
        return parts;
    }

    private static void validateOrderFormat(String orderInfo) {
        if (!orderInfo.matches((String) Constant.ORDER_FORMAT_REGEX.getValue())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

}

