package kiosk.controller.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    private final Menu menu = new Menu.Builder("ShackBurger", 6.9)
            .description("토마토, 양상추, 쉑소스가 토핑된 치즈버거")
            .options(List.of(new Option("Single", 0),
                    new Option("Double", 3.6)))
            .build();

    @DisplayName("MainCommand : can instantiate from input")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 6})
    void test(int input) {
        assertDoesNotThrow(() -> MainCommand.of(input));
    }

    @DisplayName("MainCommand : throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {-1, 7})
    void test2(int input) {
        assertThatThrownBy(() -> MainCommand.of(input));
    }

    @DisplayName("MenuCommand : can instantiate from input")
    @ParameterizedTest
    @CsvSource(value = {"1, BURGER", "2, BEER"})
    void test3(int input, Category category) {
        assertDoesNotThrow(() -> MenuCommand.of(input, category));
    }

    @DisplayName("MenuCommand : throw exception for invalid input")
    @ParameterizedTest
    @CsvSource(value = {"5, BURGER", "3, BEER"})
    void test4(int input, Category category) {
        assertThatThrownBy(() -> MenuCommand.of(input, category));
    }

    @DisplayName("OptionCommand : can instantiate from input")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void test5(int input) {
        assertDoesNotThrow(() -> OptionCommand.of(input, menu));
    }

    @DisplayName("OptionCommand : throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    void test6(int input) {
        assertThatThrownBy(() -> OptionCommand.of(input, menu));
    }


    @DisplayName("BasicCommand : can instantiate from input")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void test9(int input) {
        assertDoesNotThrow(() -> BasicCommand.of(input));
    }

    @DisplayName("BasicCommand : throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    void test10(int input) {
        assertThatThrownBy(() -> BasicCommand.of(input));
    }

    @DisplayName("ReturnCommand : can instantiate from input")
    @Test
    void test11() {
        assertDoesNotThrow(() -> ReturnCommand.of(1));
    }

    @DisplayName("BasicCommand : throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    void test12(int input) {
        assertThatThrownBy(() -> ReturnCommand.of(input));
    }

}
