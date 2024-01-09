package kiosk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import kiosk.domain.menu.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HistoryTest {

    private History history;

    @BeforeEach
    void setUp() {
        Cart cart = new Cart();
        history = new History();
        cart.addOrder(new Order(new Menu.Builder("Burger", 1000).build()));
        cart.addOrder(new Order(new Menu.Builder("Beer", 1500).build()));
        cart.addOrder(new Order(new Menu.Builder("Burger", 1000).build()));
        history.add(cart.getOrders());
    }

    @DisplayName("can add orders")
    @Test
    void test() {
        assertThat(history.size()).isEqualTo(3);
    }

    @DisplayName("can calculate total price")
    @Test
    void test2() {
        assertThat(history.totalPrice()).isEqualTo(3500);
    }
}
