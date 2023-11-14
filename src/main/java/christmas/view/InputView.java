package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.InputMessage;

public class InputView {

    public static String inputVisitDate() {
        System.out.println(InputMessage.VISIT_DATE.get());
        return Console.readLine();
    }

    public static String inputOrderInformation() {
        System.out.println(InputMessage.ORDER_INFORMATION.get());
        return Console.readLine();
    }
}
