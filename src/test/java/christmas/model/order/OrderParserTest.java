package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import christmas.common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderParserTest {

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 기본 테스트")
    @Test
    void parseOrder0() {
        //given
        String input = "티본스테이크-1,바비큐립-2,초코케이크-1,제로콜라-1";

        //when
        List<OrderItem> orderItems = OrderParser.parseOrder(input);

        // Then
        assertThat(orderItems).isNotNull();
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 테스트")
    @Test
    void parseOrder1() {
        //given
        String input = "티본스테이크-1,바비큐립-2,초코케이크-1,제로콜라-1";

        //when
        List<OrderItem> orderItems = OrderParser.parseOrder(input);

        //then
        assertThat(orderItems)
                .hasSize(4)
                .extracting(OrderItem::getMenuName, OrderItem::getQuantity)
                .contains(
                        tuple("티본스테이크", 1),
                        tuple("바비큐립", 2),
                        tuple("초코케이크", 1),
                        tuple("제로콜라", 1)
                );
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 공백 포함")
    @Test
    void parseOrder2() {
        //given
        String input = "티본스테이크 - 1, 초코케이크 - 2";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 공백만 입력")
    @Test
    void parseOrder3() {
        //given
        String input = " ";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 널값")
    @Test
    void parseOrder4() {
        //given
        String input = "";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 수량에 문자 입력")
    @Test
    void parseOrder5() {
        //given
        String input = "티본스테이크-a";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴명 한글X")
    @Test
    void parseOrder6() {
        //given
        String input = "menuName-1";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴와 수량 사이 - 존재")
    @Test
    void parseOrder7() {
        //given
        String input = "티본스테이크-1, 초코케이크,1";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - -가 여러개 존재")
    @Test
    void parseOrder8() {
        //given
        String input = "티본스테이크-1,초코케이크--1";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴명에 숫자")
    @Test
    void parseOrder9() {
        //given
        String input = "2-1";

        //when
        //then
        assertThatThrownBy(() -> OrderParser.parseOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }
}