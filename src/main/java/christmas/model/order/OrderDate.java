package christmas.model.order;

import christmas.common.ErrorMessage;
import christmas.utils.InputValueValidator;

public class OrderDate {
    private final Date date;

    private OrderDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public static OrderDate of(String input) {
        validate(input);
        Date date = new Date(parseInt(input));
        return new OrderDate(date);
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
    }

    private static void validate(String input) {
        if (InputValueValidator.isEmpty(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
        if (InputValueValidator.isContainsBlank(input) && InputValueValidator.isEndsWithComma(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
    }
}