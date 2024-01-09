package kiosk.domain.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kiosk.domain.Store;

public class Menu {

    private final String name;
    private final String description;
    private final double price;
    private final List<Option> options;

    private Menu(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.options = builder.options;
    }

    public boolean hasOption() {
        return !options.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String paddedName() {
        int longest = Store.longestNameLength();
        String pad = " ";
        return getName() + pad.repeat(longest - getName().length());
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

    public static class Builder {

        private final String name;
        private final double price;

        private String description = "";
        private List<Option> options = new ArrayList<>();

        public Builder(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder options(List<Option> options) {
            this.options = new ArrayList<>(options);
            return this;
        }

        public Menu build() {
            return new Menu(this);
        }
    }
}
