package kiosk.domain.menu;

import java.util.Objects;

public class Option {

    private final String name;
    private final double additionalPrice;

    public Option(String names, double additionalPrice) {
        this.name = names;
        this.additionalPrice = additionalPrice;
    }

    public String getName() {
        return name;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return Double.compare(additionalPrice, option.additionalPrice) == 0 && Objects.equals(name,
                option.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, additionalPrice);
    }
}
