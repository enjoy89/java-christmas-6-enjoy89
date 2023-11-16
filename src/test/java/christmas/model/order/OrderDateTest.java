package christmas.model.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderDateTest {

    @DisplayName("방문 날짜가 1~31 범위의 숫자가 아닌 경우 예외 테스트")
    @Test
    void of() {
        //given
        String day = "-1";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("방문 날짜가 숫자가 아닌 경우 예외 테스트")
    @Test
    void of2() {
        //given
        String day = "a";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("방문 날짜가 널값인 경우 예외 테스트")
    @Test
    void of3() {
        //given
        String day = "";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("방문 날짜에 공백이 포함된 경우 예외 테스트")
    @Test
    void of4() {
        //given
        String day = "1 ";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("방문 날짜 끝에 ,가 포함된 경우 예외 테스트")
    @Test
    void of5() {
        //given
        String day = "1,";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("방문 날짜에 숫자와 문자가 혼용된 경우 예외 테스트")
    @Test
    void of6() {
        //given
        String day = "1a";

        //when
        //then
        assertThatThrownBy(() -> OrderDate.of(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }
}