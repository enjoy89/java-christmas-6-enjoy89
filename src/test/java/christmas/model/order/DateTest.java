package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("날짜 객체 생성 테스트")
    @Test
    void date() {
        int day = 1;
        Date date = new Date(day);

        assertThat(date.getDay()).isEqualTo(day);
    }

    @DisplayName("날짜 객체 생성 예외 발생 테스트")
    @Test
    void date2() {
        int day = -15;

        assertThatThrownBy(() -> new Date(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트")
    @Test
    void isInRange() {
        int day = 10;
        Date date = new Date(day);
        boolean isInRange = date.isInRange(1, 25);

        assertThat(isInRange).isTrue();
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트 - 시작 날짜보다 이전일때")
    @Test
    void isInRange2() {
        int day = 1;
        Date date = new Date(day);
        boolean isInRange = date.isInRange(2, 25);

        assertThat(isInRange).isFalse();
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트 - 끝 날자보다 후일때")
    @Test
    void isInRange3() {
        int day = 26;
        Date date = new Date(day);
        boolean isInRange = date.isInRange(2, 25);

        assertThat(isInRange).isFalse();
    }

}