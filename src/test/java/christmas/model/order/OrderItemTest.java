package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @DisplayName("메뉴 이름 정보를 잘 가져오는지 기능 테스트")
    @Test
    void getMenuName() {
        //given
        OrderItem orderItem = new OrderItem("해산물파스타", 2);

        //when
        //then
        assertEquals("해산물파스타", orderItem.getMenuName());
    }

    @DisplayName("메뉴 수량 정보를 잘 가져오는지 기능 테스트")
    @Test
    void getQuantity() {
        //given
        OrderItem orderItem = new OrderItem("해산물파스타", 2);

        //when
        //then
        assertEquals(2, orderItem.getQuantity());
    }

    @DisplayName("메뉴 이름과 수량을 통해 가격을 계산하는 기능 테스트")
    @Test
    void calculateTotalPrice() {
        //given
        OrderItem orderItem = new OrderItem("해산물파스타", 2);

        //when
        int expectedTotalPrice = 2 * Menu.MAIN_3.getPrice(); // 2개의 해산물파스타의 가격

        //then
        assertEquals(expectedTotalPrice, orderItem.calculateTotalPrice());
    }

    @DisplayName("메뉴 이름과 수량을 통해 가격을 계산하는 기능 예외 테스트")
    @Test
    void calculateTotalPrice2() {
        //given
        OrderItem orderItem = new OrderItem("해산물파스타", 2);

        //when
        int expectedTotalPrice = 2 * Menu.MAIN_2.getPrice(); // 2개의 해산물파스타의 가격

        //then
        assertThat(expectedTotalPrice).isNotEqualTo(orderItem.calculateTotalPrice());
    }

    @DisplayName("메뉴 이름을 통해 메뉴 객체의 정보를 가져오는 기능 테스트")
    @Test
    void getMenuByName() {
        //given
        String menuName = "해산물파스타";
        OrderItem orderItem = new OrderItem(menuName, 2);

        //when
        Menu menu = orderItem.getMenuByOrderItem();

        //then
        assertEquals(Menu.MAIN_3, menu);
    }

    @DisplayName("메뉴 이름을 통해 메뉴 객체의 정보를 가져오는 기능 예외 테스트")
    @Test
    void getMenuByName2() {
        //given
        String menuName = "엉뚱한메뉴";
        OrderItem orderItem = new OrderItem(menuName, 2);

        //when
        //then
        assertThatThrownBy(() -> orderItem.getMenuByOrderItem().getName())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }


}