package kiosk.domain;

import kiosk.domain.menu.Menu;

public class Order {

    private final Menu menu;
    private final int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity;
    }

    public String getName() {
        return menu.getName();
    }

    public String getDescription() {
        return menu.getDescription();
    }

    public int getPrice() {
        return menu.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }
}
