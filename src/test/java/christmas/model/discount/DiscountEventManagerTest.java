package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountEventManagerTest {

    @DisplayName("할인 이벤트 매니저 객체 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-1,아이스크림-1"})
    void create(String orderInfo) {
        //given
        OrderDate orderDate = OrderDate.of("1");
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        DiscountEventManager discountEventManager = new DiscountEventManager(orderDate, orderInformation);

        //then
        assertThat(discountEventManager).isNotNull();
    }

    @DisplayName("할인 이벤트를 적용하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,제로콜라-1"})
    void applyDiscount(String orderInfo) {
        //given
        OrderDate orderDate = OrderDate.of("26");
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        DiscountEventManager discountEventManager = new DiscountEventManager(orderDate, orderInformation);
        discountEventManager.applyDiscount();

        //then
        assertThat(discountEventManager.getAppliedDiscountEvents().size()).isEqualTo(0);
    }

    @DisplayName("할인 이벤트를 적용하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void applyDiscount2(String orderInfo) {
        //given
        OrderDate orderDate = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        DiscountEventManager discountEventManager = new DiscountEventManager(orderDate, orderInformation);
        discountEventManager.applyDiscount();

        //then
        assertThat(discountEventManager.getAppliedDiscountEvents())
                .containsKey("크리스마스 디데이 할인")
                .containsKey("평일 할인")
                .containsKey("특별 할인");
    }

    @DisplayName("할인 이벤트를 적용하는 기능 테스트2")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-2,제로콜라-1"})
    void applyDiscount3(String orderInfo) {
        //given
        OrderDate orderDate = OrderDate.of("26");
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        DiscountEventManager discountEventManager = new DiscountEventManager(orderDate, orderInformation);
        discountEventManager.applyDiscount();

        //then
        assertThat(discountEventManager.getAppliedDiscountEvents())
                .containsKey("평일 할인");
    }

    @DisplayName("할인 이벤트를 적용한 금액을 구하는 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"})
    void getTotalDiscountAmount(String orderInfo) {
        //given
        OrderDate orderDate = OrderDate.of("3");
        OrderInformation orderInformation = OrderInformation.of(orderInfo);

        //when
        DiscountEventManager discountEventManager = new DiscountEventManager(orderDate, orderInformation);
        Amount totalDiscountAmount = discountEventManager.getTotalDiscountAmount();

        //then
        assertThat(totalDiscountAmount.amount()).isEqualTo(6246);
    }
}