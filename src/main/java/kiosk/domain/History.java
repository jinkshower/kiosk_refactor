package kiosk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {

    private final List<Order> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void add(Map<Order, Integer> orders) {
        for (Order order : orders.keySet()) {
            history.addAll(cloneOrder(order, orders.get(order)));
        }
    }

    private List<Order> cloneOrder(Order order, int count) {
        List<Order> cloned = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cloned.add(order);
        }
        return cloned;
    }

    public double totalPrice() {
        return history.stream()
                .mapToDouble(Order::calculatePrice)
                .sum();
    }

    public int size() {
        return history.size();
    }

    public void clear() {
        history.clear();
    }

    public String formatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : history) {
            stringBuilder.append("- ").append(order.getName())
                    .append(" | W").append(order.calculatePrice()).append("\n");
        }
        return stringBuilder.toString();
    }
}
