package kiosk.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import java.util.stream.Collectors;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoreTest {

    private List<Menu> menus;

    @BeforeEach
    void setUp() {
        menus = Store.getMenus(Category.BURGER);
    }

    @DisplayName("retrieve menus' names by category")
    @Test
    void test() {
        String text = menus.stream()
                .map(Menu::getName)
                .collect(Collectors.joining("\n"));

        assertThat(text).contains("ShackBurger", "SmokeShakck", "Shroom Burger", "Cheeseburger");
    }

    @DisplayName("can instantiate order from menus")
    @Test
    void test2() {
        assertDoesNotThrow(() -> new Order(menus.get(1)));
    }
}
