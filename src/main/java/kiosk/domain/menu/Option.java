package kiosk.domain.menu;

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
}
