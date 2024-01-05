package kiosk.controller.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OptionCommandTest {

    private static final Menu menu = new Menu("ShackBurger",
            "토마토, 양상추, 쉑소스가 토핑된 치즈버거",
            6.9, List.of(new Option("Single", 0),
            new Option("Double", 3.6)));

    @DisplayName("can instantiate from input")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void test(int input) {
        assertDoesNotThrow(() -> OptionCommand.of(input, menu));
    }

    @DisplayName("throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    void test2(int input) {
        assertThatThrownBy(() -> OptionCommand.of(input, menu));
    }

}
