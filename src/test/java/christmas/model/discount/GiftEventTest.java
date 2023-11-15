package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiftEventTest {

    @DisplayName("증정 이벤트 할인 객체 생성 테스트")
    @Test
    void of() {
        //given
        Amount totalAmount = new Amount(120000);

        //when
        GiftEvent giftEvent = GiftEvent.of(totalAmount);

        //then
        assertThat(giftEvent).isNotNull();
    }

    @DisplayName("증정 이벤트를 할인 금액 계산 테스트")
    @Test
    void calculateTotalDiscountAmount() {
        //given
        Amount totalAmount = new Amount(120000);
        GiftEvent giftEvent = GiftEvent.of(totalAmount);

        //when
        Amount amount = giftEvent.getBenefit();

        //then
        assertThat(amount.amount()).isNotEqualTo(0);
    }

    @DisplayName("증정 이벤트를 할인 금액 샴페인 가격과 같은지 테스트")
    @Test
    void calculateTotalDiscountAmount2() {
        //given
        Amount totalAmount = new Amount(120000);
        GiftEvent giftEvent = GiftEvent.of(totalAmount);

        //when
        Amount amount = giftEvent.getBenefit();

        //then
        assertThat(amount.amount()).isEqualTo(Menu.DRINK_3.getPrice()); // 샴페인 가격
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만일 때 할인 이벤트 미적용 기능 테스트")
    @Test
    void calculateTotalDiscountAmount4() {
        //given
        Amount totalAmount = new Amount(110000);
        GiftEvent giftEvent = GiftEvent.of(totalAmount);

        //when
        Amount amount = giftEvent.getBenefit();

        //then
        assertThat(amount.amount()).isEqualTo(0);
    }

}