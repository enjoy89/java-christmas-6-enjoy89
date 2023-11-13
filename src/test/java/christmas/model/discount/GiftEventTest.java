package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @DisplayName("증정 이벤트를 할인 금액 계산 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        GiftEvent giftEvent = GiftEvent.of();

        //when
        Amount amount = giftEvent.calculateTotalDiscountAmount();

        //then
        assertThat(amount.amount()).isNotEqualTo(0);
    }

    @DisplayName("증정 이벤트를 할인 금액 샴페인 가격과 같은지 테스트")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        GiftEvent giftEvent = GiftEvent.of();

        //when
        Amount amount = giftEvent.calculateTotalDiscountAmount();

        //then
        assertThat(amount.amount()).isEqualTo(Menu.DRINK_3.getPrice()); // 샴페인 가격
    }

}