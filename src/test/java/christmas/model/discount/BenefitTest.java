package christmas.model.discount;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {

    @DisplayName("배지를 부여하는 객체 생성 테스트")
    @Test
    void of() {
        //given
        Amount totalBenefit = new Amount(4000);

        //when
        Benefit benefit = Benefit.of(totalBenefit);

        //then
        assertThat(benefit).isNotNull();
    }

    @DisplayName("배지를 부여하는 기능 테스트")
    @Test
    void getBadge() {
        //given
        Amount totalBenefit = new Amount(4000);
        Benefit benefit = Benefit.of(totalBenefit);

        //when
        Badge badge = benefit.getBadge();

        //then
        assertThat(badge).isEqualTo(Badge.getDefault());
    }

    @DisplayName("배지를 부여하는 기능 테스트 - 총혜택이 5000원 이상인 경우 별 배지 부여")
    @Test
    void getBadge2() {
        //given
        Amount totalBenefit = new Amount(5000);
        Benefit benefit = Benefit.of(totalBenefit);

        //when
        Badge badge = benefit.getBadge();

        //then
        assertThat(badge).isEqualTo(Badge.STAR);
    }

    @DisplayName("배지를 부여하는 기능 테스트 - 총혜택이 10000원 이상인 경우 트리 배지 부여")
    @Test
    void getBadge3() {
        //given
        Amount totalBenefit = new Amount(10000);
        Benefit benefit = Benefit.of(totalBenefit);

        //when
        Badge badge = benefit.getBadge();

        //then
        assertThat(badge).isEqualTo(Badge.TREE);
    }

    @DisplayName("배지를 부여하는 기능 테스트 - 총혜택이 20000원 이상인 경우 산타 배지 부여")
    @Test
    void getBadge4() {
        //given
        Amount totalBenefit = new Amount(20000);
        Benefit benefit = Benefit.of(totalBenefit);

        //when
        Badge badge = benefit.getBadge();

        //then
        assertThat(badge).isEqualTo(Badge.SANTA);
    }
}