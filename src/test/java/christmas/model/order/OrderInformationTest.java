package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import christmas.model.discount.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderInformationTest {

    @DisplayName("주문 정보에 맞는 총가격 계산 기능 테스트")
    @Test
    public void testCalculateTotalOrderAmount() {
        //given
        String orderInfo = "해산물파스타-2,초코케이크-1,레드와인-1";
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        Amount totalAmount = orderInformation.getTotalAmount();

        //then
        int menu1_price = Menu.MAIN_3.getPrice() * 2;  // 해산물파스타 2개 가격
        int menu2_price = Menu.DESSERT_1.getPrice(); // 초코케이크 1개 가격
        int menu3_price = Menu.DRINK_2.getPrice(); // 레드와인 1개 가격
        assertThat(totalAmount.getAmount()).isEqualTo(menu1_price + menu2_price + menu3_price);
    }

    @DisplayName("주문 정보에 맞는 총가격 계산 기능 테스트2")
    @Test
    public void testCalculateTotalOrderAmount2() {
        //given
        String orderInfo = "해산물파스타-2,아이스크림-2,샴페인-1";
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        Amount totalAmount = orderInformation.getTotalAmount();

        //then
        int menu1_price = Menu.MAIN_3.getPrice() * 2;  // 해산물파스타 2개 가격
        int menu2_price = Menu.DESSERT_2.getPrice() * 2; // 아이스크림 2개 가격
        int menu3_price = Menu.DRINK_3.getPrice(); // 샴페인 1개 가격
        assertThat(totalAmount.getAmount()).isEqualTo(menu1_price + menu2_price + menu3_price);
    }

    @DisplayName("음료 메뉴만 입력하는 경우 예외 발생 테스트")
    @Test
    void validate() {
        //given
        String orderInfo = "제로콜라-1,레드와인-1,샴페인-1";

        //when
        //then
        assertThatThrownBy(() -> OrderInformation.of(orderInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("메뉴 수량이 1이상의 숫자가 아닌 경우 예외 발생 테스트")
    @Test
    void validate2() {
        //given
        String orderInfo = "양송이수프-0";

        //when
        //then
        assertThatThrownBy(() -> OrderInformation.of(orderInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("메뉴 수량이 20개를 초과한 경우 예외 발생 테스트")
    @Test
    void validate3() {
        //given
        String orderInfo = "양송이수프-5,티본스테이크-5,해산물파스타-5,제로콜라-10";

        //when
        //then
        assertThatThrownBy(() -> OrderInformation.of(orderInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("메뉴 수량이 20개를 초과한 경우 예외 발생 테스트2")
    @Test
    void validate4() {
        //given
        String orderInfo = "양송이수프-0,제로콜라20";

        //when
        //then
        assertThatThrownBy(() -> OrderInformation.of(orderInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

}