package kiosk.domain.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Menu {

    private final String name;
    private final String description;
    private final double price;
    private final List<Option> options;

    public Menu(String name, String description, double price, List<Option> options) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.options = new ArrayList<>(options);
    }

    public Menu(String name, double price) {
        this.name = name;
        this.price = price;
        this.description = "";
        this.options = new ArrayList<>();
    }

    public Menu(String name, String description, double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.options = new ArrayList<>();
    }

    public boolean hasOption() {
        return !options.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public List<Option> getOptions() {
        return new ArrayList<>(options);
    }

    public int optionsCount() {
        return options.size();
    }

    public String formatted() {
        return name + " | W " + price + " | " + description;
    }

    public String optionMessage() {
        int index = 1;
        StringBuilder numbered = new StringBuilder();

        for (Option option : options) {
            numbered.append(index).append(". ").append(option.getName()).append("(W")
                    .append(price + option.getAdditionalPrice()).append(")").append("        ");
            index++;
        }
        return numbered.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return price == menu.price && Objects.equals(name, menu.name) && Objects.equals(description,
                menu.description) && Objects.equals(options, menu.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, options);
    }
}
