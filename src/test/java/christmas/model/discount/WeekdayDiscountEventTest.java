package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountEventTest {

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(3);
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, Menu.DESSERT_1);

        //when
        boolean isPossibleEvent = weekdayDiscountEvent.isPossibleEvent(date);

        //then
        assertThat(isPossibleEvent).isTrue();
    }

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(8);    //주말

        //when
        //then
        assertThatThrownBy(() -> WeekdayDiscountEvent.of(date, Menu.DESSERT_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_DATE_WEEKDAY_EVENT.get());
    }


    @DisplayName("디저트 메뉴에 평일 할인 이벤트를 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Date date = new Date(3);
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, Menu.DESSERT_1);

        //when
        int discountAmount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.DESSERT_1.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());

    }

    @DisplayName("디저트 메뉴에 평일 할인 이벤트를 적용하는 기능 테스트2")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        Date date = new Date(3);
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, Menu.DESSERT_2);

        //when
        int discountAmount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.DESSERT_2.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());

    }
}