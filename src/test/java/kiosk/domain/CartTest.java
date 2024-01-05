package kiosk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import kiosk.domain.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CartTest {

    private Cart cart;
    private Order order;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.addOrder(new Order(new Menu("Burger", 1000)));
        cart.addOrder(new Order(new Menu("Beer", 1500)));
        order = new Order(new Menu("Beer", 1500));
    }

    @DisplayName("size check")
    @Test
    void test() {
        assertThat(cart.getOrders().size()).isEqualTo(2);
    }

    @DisplayName("clear check")
    @Test
    void test2() {
        cart.clear();
        assertThat(cart.getOrders().size()).isEqualTo(0);
    }

    @DisplayName("remove chcek")
    @Test
    void test3() {
        cart.removeOrder(order);
        assertThat(cart.getOrders().size()).isEqualTo(1);
    }

    @DisplayName("Calculate total price")
    @Test
    void test4() {
        double actual = cart.calculateTotalPrice();

        assertThat(actual).isEqualTo(2500);
    }

}
