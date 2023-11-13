package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.OrderDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDiscountEventTest {

    @DisplayName("특별 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        OrderDate date = OrderDate.of("3");
        SpecialDiscountEvent specialDiscountEvent = SpecialDiscountEvent.of(date);

        //when
        Amount discount = specialDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(1000);
    }

    @DisplayName("특별 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        OrderDate date = OrderDate.of("4");
        SpecialDiscountEvent specialDiscountEvent = SpecialDiscountEvent.of(date);

        //when
        Amount discount = specialDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(0);
    }

    @DisplayName("특별 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent3() {
        //given
        OrderDate date = OrderDate.of("10");
        SpecialDiscountEvent specialDiscountEvent = SpecialDiscountEvent.of(date);

        //when
        Amount discount = specialDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isNotEqualTo(0);
    }

}