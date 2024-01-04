package kiosk.domain;

import java.util.List;

public class Option {

    private final List<String> names;
    private final int additionalPrice;

    public Option(List<String> names, int additionalPrice) {
        this.names = names;
        this.additionalPrice = additionalPrice;
    }

    public List<String> getNames() {
        return names;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }
}
