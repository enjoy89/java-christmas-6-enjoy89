package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 테스트")
    @Test
    void create0() {
        //given
        String input = "티본스테이크-1,바비큐립-2,초코케이크-1,제로콜라-1";

        //when
        OrderMenus orderMenus = OrderMenus.of(input);

        // Then
        assertThat(orderMenus).isNotNull();
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 테스트")
    @Test
    void create1() {
        //given
        String input = "티본스테이크-1,바비큐립-2,초코케이크-1,제로콜라-1";

        //when
        OrderMenus orderMenus = OrderMenus.of(input);

        // Then
        assertThat(orderMenus).isNotNull();
        assertThat(orderMenus.getOrders())
                .containsEntry("티본스테이크", 1)
                .containsEntry("바비큐립", 2)
                .containsEntry("초코케이크", 1)
                .containsEntry("제로콜라", 1);
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 테스트2")
    @Test
    void create2() {
        //given
        String input = "티본스테이크-1";

        //when
        OrderMenus orderMenus = OrderMenus.of(input);

        // Then
        assertThat(orderMenus).isNotNull();
        assertThat(orderMenus.getOrders())
                .containsEntry("티본스테이크", 1);
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 공백 포함")
    @Test
    void create3() {
        //given
        String input = "티본스테이크 - 1, 초코케이크 - 2";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 공백만 입력")
    @Test
    void create4() {
        //given
        String input = " ";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 널값")
    @Test
    void create5() {
        //given
        String input = "";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 수량에 문자 입력")
    @Test
    void create6() {
        //given
        String input = "티본스테이크-a";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴명 한글X")
    @Test
    void create7() {
        //given
        String input = "menuName-1";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴와 수량 사이 - 존재")
    @Test
    void create8() {
        //given
        String input = "티본스테이크-1, 초코케이크,1";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - -가 여러개 존재")
    @Test
    void create9() {
        //given
        String input = "티본스테이크-1,초코케이크--1";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

    @DisplayName("주문의 입력이 올바른지 검사하는 기능 예외 테스트 - 메뉴명에 숫자")
    @Test
    void create10() {
        //given
        String input = "2-1";

        //when
        //then
        assertThatThrownBy(() -> OrderMenus.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.get());
    }

}