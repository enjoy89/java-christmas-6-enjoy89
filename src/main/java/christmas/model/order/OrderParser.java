package christmas.model.order;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.utils.InputValueValidator;
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
        return orderInfo.split(",");
    }

    private static String[] splitOrderItem(String orderItem) {
        String[] parts = orderItem.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
        return parts;
    }

    private static void validateOrderFormat(String orderInfo) {
        if (!orderInfo.matches((String) Constant.ORDER_FORMAT_REGEX.getValue())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }

        // 이게 사용될까? 위에 조건문에서 다 걸러지는 것 같으면 삭제해야된다.
        if (InputValueValidator.isContainsBlank(orderInfo) || InputValueValidator.isEndsWithComma(orderInfo)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
        }
    }

}

