package kiosk.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Order> orders;

    public Cart() {
        this.orders = new ArrayList<>();
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += order.calculatePrice();
        }
        return totalPrice;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void clear() {
        orders.clear();
    }
}
