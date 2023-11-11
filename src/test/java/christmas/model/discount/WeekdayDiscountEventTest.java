package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountEventTest {

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(3);
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date);

        //when
        boolean isPossibleEvent = weekdayDiscountEvent.isPossibleEvent(date);

        assertThat(isPossibleEvent).isTrue();
    }

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(8);    //주말

        //when
        //then
        assertThatThrownBy(() -> WeekdayDiscountEvent.of(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_DATE_WEEKDAY_EVENT.get());
    }

    @Test
    void calculateTotalDiscountAmount() {
    }
}