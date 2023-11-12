package christmas.utils;

import christmas.common.Constant;

public class InputValueValidator {

    public static boolean isEmpty(String input) {
        return input.isEmpty();
    }

    public static boolean isContainsBlank(String input) {
        return input.contains((String) Constant.BLANK.getValue());
    }

    public static boolean isEndsWithComma(String input) {
        return input.endsWith((String) Constant.COMMA.getValue());
    }
}
