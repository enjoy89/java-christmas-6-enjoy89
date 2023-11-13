package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.common.Constant;
import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountEventTest {

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        OrderDate date = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of("초코케이크-1");
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isNotEqualTo(0);
    }

    @DisplayName("평일 할인 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        OrderDate date = OrderDate.of("8"); // 주말
        OrderInformation orderInformation = OrderInformation.of("초코케이크-1");
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(0);
    }


    @DisplayName("디저트 메뉴에 평일 할인 이벤트를 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        OrderDate date = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of("초코케이크-1");
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo(Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue());

    }

    @DisplayName("디저트 메뉴에 평일 할인 이벤트를 적용하는 기능 테스트2")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        OrderDate date = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of("아이스크림-2");
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo((int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * 2);

    }

    @DisplayName("디저트 메뉴에 평일 할인 이벤트를 적용하는 기능 테스트3")
    @Test
    void calculateTotalDiscountAmount3() {
        //given
        OrderDate date = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of("초코케이크-1,아이스크림-2");  //3개
        WeekdayDiscountEvent weekdayDiscountEvent = WeekdayDiscountEvent.of(date, orderInformation);

        //when
        Amount discount = weekdayDiscountEvent.calculateTotalDiscountAmount();

        //then
        assertThat(discount.amount()).isEqualTo((int) Constant.WEEKDAY_WEEKEND_DISCOUNT_AMOUNT.getValue() * 3);

    }
}