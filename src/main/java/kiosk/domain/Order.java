package kiosk.domain;

import java.util.Objects;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;

public class Order {

    private final Menu menu;
    private final Option chosenOption;

    public Order(Menu menu, Option chosenOption) {
        this.menu = menu;
        this.chosenOption = chosenOption;
    }

    public Order(Menu menu) {
        this.menu = menu;
        this.chosenOption = new Option("", 0);
    }

    public double calculatePrice() {
        return menu.getPrice() + chosenOption.getAdditionalPrice();
    }

    public String formatted() {
        String option = "";
        if (menu.hasOption()) {
            option = chosenOption.formatted();
        }
        return menu.paddedName() + option + " | W " +
                (menu.getPrice() + chosenOption.getAdditionalPrice()) + " | " + menu.getDescription();
    }

    public String getName() {
        return menu.paddedName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(menu, order.menu) && Objects.equals(chosenOption, order.chosenOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, chosenOption);
    }
}
