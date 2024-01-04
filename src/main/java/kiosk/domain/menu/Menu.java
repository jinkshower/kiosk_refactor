package kiosk.domain.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final String name;
    private final String description;
    private final int price;
    private final List<Option> options;

    public Menu(String name, String description, int price, List<Option> options) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.options = new ArrayList<>(options);
    }

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
        this.description = "";
        this.options = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public List<Option> getOptions() {
        return new ArrayList<>(options);
    }
}
