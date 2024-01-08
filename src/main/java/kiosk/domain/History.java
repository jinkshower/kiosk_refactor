package kiosk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class History {

    private static final List<Order> history = new ArrayList<>();

    public static void add(Map<Order, Integer> orders) {
        for (Order order : orders.keySet()) {
            history.addAll(cloneOrder(order, orders.get(order)));
        }
    }

    private static List<Order> cloneOrder(Order order, int count) {
        List<Order> cloned = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cloned.add(order);
        }
        return cloned;
    }

    public static double totalPrice() {
        return history.stream()
                .mapToDouble(Order::calculatePrice)
                .sum();
    }

    public static int size() {
        return history.size();
    }

    public static void clear() {
        history.clear();
    }

    public static String formatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : history) {
            stringBuilder.append("- ").append(order.getName())
                    .append(" | W").append(order.calculatePrice()).append("\n");
        }
        return stringBuilder.toString();
    }
}
