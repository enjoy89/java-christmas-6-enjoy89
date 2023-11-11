package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountEventTest {

    @DisplayName("특별 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(3);
        SpecialDiscountEvent specialDiscountEvent = SpecialDiscountEvent.of(date);

        //when
        boolean isPossibleEvent = specialDiscountEvent.isPossibleEvent(date);

        //then
        assertThat(isPossibleEvent).isTrue();
    }

    @DisplayName("특별 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(4);

        //when
        //then
        assertThatThrownBy(() -> SpecialDiscountEvent.of(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_DATE_SPECIAL_EVENT.get());
    }

    @DisplayName("총 주문 금액에서 특별 할인 이벤트를 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Date date = new Date(3);
        SpecialDiscountEvent specialDiscountEvent = SpecialDiscountEvent.of(date);

        //when
        int discountAmount = specialDiscountEvent.calculateTotalDiscountAmount();

        assertThat(discountAmount).isEqualTo(1000);
    }
}