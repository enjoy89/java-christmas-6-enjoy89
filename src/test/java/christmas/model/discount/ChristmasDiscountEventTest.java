package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountEventTest {

    @DisplayName("방문 날짜를 확인하여 크리스마스 디데이 할인 이벤트를 적용할 수 있는지 검사한다.")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(1);
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date, new Amount(10000));

        //when
        boolean isPossibleEvent = christmasDiscountEvent.isPossibleEvent(date);

        //then
        assertThat(isPossibleEvent).isTrue();
    }

    @DisplayName("크리스마스 디데이 할인 이벤트를 적용할 수 없는 경우 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(26);
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date, new Amount(10000));

        //when
        boolean isPossibleEvent = christmasDiscountEvent.isPossibleEvent(date);

        //then
        assertThat(isPossibleEvent).isFalse();
    }

}