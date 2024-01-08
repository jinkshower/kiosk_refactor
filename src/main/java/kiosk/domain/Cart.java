package kiosk.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<Order, Integer> orders;

    public Cart() {
        this.orders = new HashMap<>();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Order order : orders.keySet()) {
            totalPrice += order.calculatePrice() * orders.get(order);
        }
        return totalPrice;
    }

    public Map<Order, Integer> getOrders() {
        return new HashMap<>(orders);
    }

    public void addOrder(Order order) {
        int count = orders.getOrDefault(order, 0);
        orders.put(order, count + 1);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void clear() {
        orders.clear();
    }

    public String formatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : orders.keySet()) {
            stringBuilder.append(order.formatted()).append(" | ")
                    .append(orders.get(order)).append("개").append("\n");
        }
        return stringBuilder.toString();
    }
}
