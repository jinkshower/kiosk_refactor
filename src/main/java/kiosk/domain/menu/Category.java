package kiosk.domain.menu;

public enum Category {

    BURGER("Burgers         ", " 앵거스 비프 통살을 다져만든 버거"),
    FROZEN_CUSTARD("Frozen Custard  ", " 매장에서 신선하게 만드는 아이스크림"),
    DRINK("Drinks          ", " 매장에서 직접 만드는 음료"),
    BEER("Beer            ", " 뉴욕 브루클린 브루어리에서 양조한 맥주");

    private final String name;
    private final String description;

    Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getMessage() {
        return name + "|" + description;
    }
}
