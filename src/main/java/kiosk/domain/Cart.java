package kiosk.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kiosk.domain.menu.Menu;

public class Cart {

    private final Map<Order, Integer> orders;

    public Cart() {
        this.orders = new HashMap<>();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for(Order order : orders.keySet()) {
            totalPrice += order.calculatePrice() * orders.get(order);
        }
        return totalPrice;
    }

    public Map<Order, Integer> getOrders() {
        return new HashMap<>(orders);
    }

    public void addOrder(Order order) {
        int count = orders.getOrDefault(order ,0);
        orders.put(order, count + 1);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void clear() {
        orders.clear();
    }
}
