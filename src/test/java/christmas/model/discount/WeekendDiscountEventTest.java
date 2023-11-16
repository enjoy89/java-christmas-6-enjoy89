package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.Constant;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountEventTest {

    @DisplayName("주말 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("티본스테이크-1");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isNotEqualTo(0);
    }

    @DisplayName("주말 할인 이벤트를 평일에 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        OrderDate date = OrderDate.of("4"); // 평일
        OrderInformation orderInformation = OrderInformation.of("티본스테이크-1");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(0);
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("티본스테이크-1");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트2")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("바비큐립-1");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트3")
    @Test
    void calculateTotalDiscountAmount3() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("해산물파스타-2");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo((int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * 2);
    }

    @DisplayName("메인 메뉴에 주말 할인 이벤트를 적용하느 기능 테스트4")
    @Test
    void calculateTotalDiscountAmount4() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("크리스마스파스타-2");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo((int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * 2);
    }

    @DisplayName("애피타이저 메뉴에 주말 할인 이벤트를 적용하는 기능 예외 테스트")
    @Test
    void calculateTotalDiscountAmount5() {
        //given
        OrderDate date = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of("양송이수프-2");
        WeekendDiscountEvent weekendDiscountEvent = WeekendDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekendDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(0);
    }
}