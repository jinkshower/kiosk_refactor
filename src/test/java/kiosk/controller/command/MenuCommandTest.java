package kiosk.controller.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import kiosk.domain.menu.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuCommandTest {

    @DisplayName("can instantiate from input")
    @ParameterizedTest
    @CsvSource(value = {"1, BURGER", "2, BEER"})
    void test(int input, Category category) {
        assertDoesNotThrow(() -> MenuCommand.of(input, category));
    }

    @DisplayName("can instantiate from input")
    @ParameterizedTest
    @CsvSource(value = {"5, BURGER", "3, BEER"})
    void test2(int input, Category category) {
        assertThatThrownBy(() -> MenuCommand.of(input, category));
    }
}
