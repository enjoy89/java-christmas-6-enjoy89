package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.OrderDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountEventTest {

    @DisplayName("방문 날짜를 확인하여 크리스마스 디데이 할인 이벤트를 적용할 수 있는지 검사한다.")
    @Test
    void isPossibleEvent() {
        //given
        OrderDate date = OrderDate.of("1");
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);

        //when
        Amount amount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(amount).isNotEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인 이벤트를 적용할 수 없는 경우 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        OrderDate date = OrderDate.of("26");
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);

        //when
        Amount amount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(amount.amount()).isEqualTo(0);
    }

    @DisplayName("12월 1일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        OrderDate date = OrderDate.of("1");
        Amount discountAmount = new Amount(1000); // 12월 1일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        Amount totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

    @DisplayName("12월 10일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        OrderDate date = OrderDate.of("10");
        Amount discountAmount = new Amount(1900); // 12월 10일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        Amount totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

    @DisplayName("12월 25일에 크리스마스 디데이 할인 이벤트 적용 시 할인 받는 금액을 구하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount3() {
        //given
        OrderDate date = OrderDate.of("25");
        Amount discountAmount = new Amount(3400); // 12월 25일에 받아야하는 할인 금액

        //when
        ChristmasDiscountEvent christmasDiscountEvent = ChristmasDiscountEvent.of(date);
        Amount totalDiscountAmount = christmasDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount).isEqualTo(discountAmount);
    }

}