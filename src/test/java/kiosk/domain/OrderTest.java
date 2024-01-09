package kiosk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import kiosk.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("price times quantity calculation")
    @Test
    void test() {
        Order order = new Order(new Menu.Builder("Burger", 1000).build());

        assertThat(order.calculatePrice()).isEqualTo(1000);
    }
}
