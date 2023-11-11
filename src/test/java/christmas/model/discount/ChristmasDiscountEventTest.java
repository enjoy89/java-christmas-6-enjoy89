package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountEventTest {

    @DisplayName("방문 날짜를 확인하여 크리스마스 디데이 할인 이벤트를 적용할 수 있는지 검사한다.")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(1);
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);

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

        assertThatThrownBy(() -> ChristmasDiscountEvent.of(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_DATE_CHRISTMAS_EVENT.get());
    }

    @DisplayName("12월 1일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Date date = new Date(1);
        int discountAmount = 1000; // 12월 1일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        int totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

    @DisplayName("12월 10일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        Date date = new Date(10);
        int discountAmount = 1900; // 12월 10일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        int totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

    @DisplayName("12월 25일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount3() {
        //given
        Date date = new Date(25);
        int discountAmount = 3400; // 12월 25일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        int totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

}