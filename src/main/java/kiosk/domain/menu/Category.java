package kiosk.domain.menu;

import java.util.Arrays;

public enum Category {

    BURGER("Burgers         ", " 앵거스 비프 통살을 다져만든 버거", 1),
    FROZEN_CUSTARD("Frozen Custard  ", " 매장에서 신선하게 만드는 아이스크림", 2),
    DRINK("Drinks          ", " 매장에서 직접 만드는 음료", 3),
    BEER("Beer            ", " 뉴욕 브루클린 브루어리에서 양조한 맥주", 4);

    private final String name;
    private final String description;
    private final int categoryNumber;

    Category(String name, String description, int categoryNumber) {
        this.name = name;
        this.description = description;
        this.categoryNumber = categoryNumber;
    }

    public static Category from(int input) {
        return Arrays.stream(values())
                .filter(category -> category.categoryNumber == input)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]올바른 카테고리가 아닙니다"));
    }

    public String getMessage() {
        return name + "|" + description;
    }
}
