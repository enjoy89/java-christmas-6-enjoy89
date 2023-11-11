package christmas.model.discount;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import christmas.model.order.Date;
import christmas.model.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @DisplayName("증정 이벤트를 적용할 수 있는지 검사하는 기능 테스트")
    @Test
    void isPossibleEvent() {
        //given
        Date date = new Date(1);
        Amount totalAmount = new Amount(120_000);

        //when
        GiftEvent giftEvent = GiftEvent.of(date, totalAmount);

        //then
        assertThat(giftEvent).isNotNull();
    }

    @DisplayName("증정 이벤트를 적용할 수 있는지 검사하는 기능 예외 테스트")
    @Test
    void isPossibleEvent2() {
        //given
        Date date = new Date(1);
        Amount totalAmount = new Amount(10000);

        //when
        //then
        assertThatThrownBy(() -> GiftEvent.of(date, totalAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.IMPOSSIBLE_GIFT_EVENT.get());
    }

    @DisplayName("증정 이벤트 적용하는 기능 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Date date = new Date(1);
        Amount totalAmount = new Amount(120_000);

        //when
        GiftEvent giftEvent = GiftEvent.of(date, totalAmount);
        int giftAmount = giftEvent.calculateTotalAmount();

        //then
        assertThat(giftAmount).isEqualTo(Menu.DRINK_3.getPrice());

    }

    @DisplayName("증정 이벤트 적용하는 기능 예외 테스트")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        Date date = new Date(1);
        Amount totalAmount = new Amount(120_000);

        //when
        GiftEvent giftEvent = GiftEvent.of(date, totalAmount);
        int giftAmount = giftEvent.calculateTotalAmount();

        //then
        assertThat(giftAmount).isNotEqualTo(1000); // 샴페인을 증정하므로, 이와 다른 아무 가격이나 입력

    }
}