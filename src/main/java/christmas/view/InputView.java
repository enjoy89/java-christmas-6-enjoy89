package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.InputMessage;

public class InputView {

    public static void inputVisitDate() {
        System.out.println(InputMessage.VISIT_DATE);
        Console.readLine();
    }

    public static void inputOrderInformation() {
        System.out.println(InputMessage.ORDER_INFORMATION);
    }
}
