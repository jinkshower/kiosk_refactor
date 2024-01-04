package kiosk.domain;

import java.util.Objects;
import kiosk.domain.menu.Menu;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public double calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public String getName() {
        return menu.getName();
    }

    public String getDescription() {
        return menu.getDescription();
    }

    public double getPrice() {
        return menu.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return quantity == order.quantity && Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, quantity);
    }
}
