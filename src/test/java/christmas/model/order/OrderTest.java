package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.discount.Amount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private static Order order;

    @BeforeEach
    void setUp() {
        OrderDate orderDate = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        order = new Order(orderDate, orderInformation);
    }

    @DisplayName("총혜택 금액을 계산하는 기능 테스트")
    @Test
    void getTotalBenefit() {
        //given
        //when
        Amount totalBenefit = order.getTotalBenefit();

        //then
        assertThat(totalBenefit.amount()).isEqualTo(31246);
    }

    @DisplayName("총혜택 금액에서 증정 이벤트 가격을 제외하는 기능 테스트")
    @Test
    void getTotalBenefitExceptGiftBenefit() {
        //given
        //when
        Amount totalBenefitExceptGiftBenefit = order.getTotalBenefitExceptGiftBenefit();

        //then
        assertThat(totalBenefitExceptGiftBenefit.amount()).isEqualTo(6246);
    }
}