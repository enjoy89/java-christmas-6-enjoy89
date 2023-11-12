package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.Constant;
import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import christmas.model.order.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountEventTest {

    @DisplayName("주말 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(1);
        OrderItem orderItem = new OrderItem("티본스테이크", 1);
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderItem);

        //when
        boolean isPossibleEvent = weekendDiscountEvent.isPossibleEvent(date);

        //then
        assertThat(isPossibleEvent).isTrue();
    }

    @DisplayName("주말 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(4);
        OrderItem orderItem = new OrderItem("티본스테이크", 1);

        //when
        //then
        assertThatThrownBy(() -> WeekendDiscountEvent.of(date, orderItem))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_DATE_WEEKEND_EVENT.get());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Date date = new Date(1);
        OrderItem orderItem = new OrderItem("티본스테이크", 1);
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderItem);

        //when
        int discountAmount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.MAIN_1.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트2")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        Date date = new Date(1);
        OrderItem orderItem = new OrderItem("바비큐립", 1);
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderItem);

        //when
        int discountAmount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.MAIN_2.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트3")
    @Test
    void calculateTotalDiscountAmount3() {
        //given
        Date date = new Date(1);
        OrderItem orderItem = new OrderItem("해산물파스타", 1);
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderItem);

        //when
        int discountAmount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.MAIN_3.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트4")
    @Test
    void calculateTotalDiscountAmount4() {
        //given
        Date date = new Date(1);
        OrderItem orderItem = new OrderItem("크리스마스파스타", 1);
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderItem);

        //when
        int discountAmount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discountAmount).isEqualTo(Menu.MAIN_4.getPrice()
                - (int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }
}