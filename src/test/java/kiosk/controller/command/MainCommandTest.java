package kiosk.controller.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import kiosk.domain.menu.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MainCommandTest {

    @DisplayName("can instantiate from input")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 6})
    void test(int input) {
        assertDoesNotThrow(() -> MainCommand.of(input));
    }

    @DisplayName("throw exception for invalid input")
    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void test2(int input) {
        assertThatThrownBy(() -> MainCommand.of(input));
    }
}
