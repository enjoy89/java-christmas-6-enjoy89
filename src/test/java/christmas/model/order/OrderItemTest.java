package christmas.model.order;

import static org.junit.jupiter.api.Assertions.*;

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

}