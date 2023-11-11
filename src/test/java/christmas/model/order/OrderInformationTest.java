package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

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
        int menu1_price = Menus.MAIN_3.getPrice() * 2;  // 해산물파스타 2개 가격
        int menu2_price = Menus.DESSERT_1.getPrice() * 1; // 초코케이크 1개 가격
        int menu3_price = Menus.DRINK_2.getPrice() * 1; // 레드와인 1개 가격
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
        int menu1_price = Menus.MAIN_3.getPrice() * 2;  // 해산물파스타 2개 가격
        int menu2_price = Menus.DESSERT_2.getPrice() * 2; // 초코케이크 1개 가격
        int menu3_price = Menus.DRINK_3.getPrice() * 1; // 레드와인 1개 가격
        assertThat(totalAmount.getAmount()).isEqualTo(menu1_price + menu2_price + menu3_price);
    }

}