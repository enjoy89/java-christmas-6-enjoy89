package christmas.model.order;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.common.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("날짜 객체 생성 테스트")
    @Test
    void date() {
        //given
        int day = 1;

        //when
        Date date = new Date(day);

        //then
        assertThat(date.getDay()).isEqualTo(day);
    }

    @DisplayName("날짜 객체 생성 예외 발생 테스트")
    @Test
    void date2() {
        //given
        int day = -15;

        //when
        //then
        assertThatThrownBy(() -> new Date(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.get());
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트")
    @Test
    void isInRange() {
        //given
        int day = 10;

        //when
        Date date = new Date(day);
        boolean isInRange = date.isInRange(1, 25);

        //then
        assertThat(isInRange).isTrue();
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트 - 시작 날짜보다 이전일때")
    @Test
    void isInRange2() {
        //given
        int day = 1;

        //when
        Date date = new Date(day);
        boolean isInRange = date.isInRange(2, 25);

        //then
        assertThat(isInRange).isFalse();
    }

    @DisplayName("날짜 범위가 주어졌을 때, 이 범위에 속하는지 비교 테스트 - 끝 날자보다 후일때")
    @Test
    void isInRange3() {
        //given
        int day = 26;

        //when
        Date date = new Date(day);
        boolean isInRange = date.isInRange(2, 25);

        //then
        assertThat(isInRange).isFalse();
    }

    @DisplayName("날짜가 주말인지 검사하는 기능 테스트")
    @Test
    void isWeekend() {
        //given
        int day = 1;

        //when
        Date date = new Date(day);
        boolean isWeekend = date.isWeekend(day);

        //then
        assertThat(isWeekend).isTrue();
    }

    @DisplayName("날짜가 주말인지 검사하는 기능 예외 테스트")
    @Test
    void isWeekend2() {
        //given
        int day = 13; // 수요일

        //when
        Date date = new Date(day);
        boolean isWeekend = date.isWeekend(day);

        //then
        assertThat(isWeekend).isFalse();
    }

    @DisplayName("날짜가 평일인지 검사하는 기능 테스트")
    @Test
    void isWeekday() {
        //given
        int day = 4;

        //when
        Date date = new Date(day);
        boolean isWeekday = date.isWeekday(day);

        //then
        assertThat(isWeekday).isTrue();
    }

    @DisplayName("날짜가 평일인지 검사하는 기능 테스트2")
    @Test
    void isWeekday2() {
        //given
        int day = 3; // 일요일

        //when
        Date date = new Date(day);
        boolean isWeekday = date.isWeekday(day);

        //then
        assertThat(isWeekday).isTrue();
    }

    @DisplayName("날짜가 평일인지 검사하는 기능 예외 테스트")
    @Test
    void isWeekday3() {
        //given
        int day = 16; // 토요일

        //when
        Date date = new Date(day);
        boolean isWeekday = date.isWeekday(day);

        //then
        assertThat(isWeekday).isFalse();
    }

    @DisplayName("날짜가 특별한 날인지 검사하는 기능 테스트")
    @Test
    void isSpecialDay() {
        //given
        int day = 10;

        //when
        Date date = new Date(day);
        boolean isSpecialDay = date.isSpecialDay(day);

        //then
        assertThat(isSpecialDay).isTrue();
    }

    @DisplayName("날짜가 특별한 날인지 검사하는 기능 예외 테스트")
    @Test
    void isSpecialDay2() {
        //given
        int day = 11;

        //when
        Date date = new Date(day);
        boolean isSpecialDay = date.isSpecialDay(day);

        //then
        assertThat(isSpecialDay).isFalse();
    }
}