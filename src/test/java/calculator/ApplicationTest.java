package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

class ApplicationTest extends NsTest {

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("기본 구분자 입력에 대한 테스트")
    void endToEnd_Test_1() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("구분자 없이 단일 입력에 대한 테스트")
    void endToEnd_Test_2() {
        assertSimpleTest(() -> {
            run("1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    @DisplayName("커스텀 구분자 입력에 대한 덧셈을 테스트")
    void endToEnd_Test_3() {
        assertSimpleTest(() -> {
            run("//.\\n1.2,3:4.5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    @DisplayName("커스텀 구분자 입력에 대한 덧셈을 테스트")
    void endToEnd_Test_4() {
        assertSimpleTest(() -> {
            run("//.\\n1.2,3:4.5");
            assertThat(output()).contains("결과 : 15");
        });
    }

    @Test
    @DisplayName("빈 문자열 입력에 대해 테스트")
    void endToEnd_Test_5() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("커스텀 구분자가 - 일 때, 음수 입력시 예외가 발생하는지 테스트")
    void endToEnd_Test_6() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//-\\n1,-2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("커스텀 구분자가 두 개 이상 일 때, 예외가 발생하는지 테스트")
    void endToEnd_Test_7() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n\\n1\\n2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("양 끝에 공백을 입력 했을 때, 예외가 발생하는지 테스트")
    void endToEnd_Test_8() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException(" 1,2,3 "))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    @DisplayName("맨 앞에 공백을 입력 했을 때, 예외가 발생하는지 테스트")
    void endToEnd_Test_9() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException(" 1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    @DisplayName("맨 뒤에 공백을 입력 했을 때, 정상동작하는지 테스트")
    void endToEnd_Test_10() {
        assertSimpleTest(() -> {
            run("1,2,3 ");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
