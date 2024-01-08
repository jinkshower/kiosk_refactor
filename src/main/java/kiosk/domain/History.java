package kiosk.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {

    private static final List<Order> history = new ArrayList<>();

    public static void add(Map<Order, Integer> orders) {
        history.addAll(orders.keySet());
    }

    public static double totalPrice() {
        return history.stream()
                .mapToDouble(Order::calculatePrice)
                .sum();
    }

    public static String formatted() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Order order : history) {
            stringBuilder.append("- ").append(order.getName()).append(" | W").append(order.calculatePrice());
        }
        return stringBuilder.toString();
    }
}
