package kiosk.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;

public class Store {

    public static final Map<Category, List<Menu>> menus = new EnumMap<>(Category.class);

    static {
        menus.put(Category.BURGER,
                List.of(new Menu("ShackBurger",
                        "토마토, 양상추, 쉑소스가 토핑된 치즈버거",
                        6.9, List.of(new Option("Single", 0),
                                           new Option("Double", 3.6))),
                        new Menu("SmokeShakck",
                                "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
                                8.9, List.of(new Option("Single", 0),
                                                   new Option("Double", 3.6))),
                        new Menu("Shroom Burger",
                                "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거",
                                9.4, List.of(new Option("Single", 0),
                                                   new Option("Double", 3.6))),
                        new Menu("Cheeseburger",
                                "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
                                6.9, List.of(new Option("Single", 0),
                                                   new Option("Double", 3.6)))
                        ));
        menus.put(Category.FROZEN_CUSTARD,
                List.of(new Menu("Shake of the Week",
                        "특별한 커스터드 플레이버",
                        6.5),
                        new Menu("Red Bean Shake",
                                "신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크",
                                6.5),
                        new Menu("Floats",
                                "루트 비어, 퍼플 카우, 크림시클",
                                5.9)
                ));
        menus.put(Category.DRINK,
                List.of(new Menu("Shack-made Lemonade",
                                "매장에서 직접 만드는 상큼한 레몬에이드",
                                3.9, List.of(new Option("Regular", 0),
                                                    new Option("Large", 0.6))),
                        new Menu("Fresh Brewed Iced Tea",
                                "직접 유기농 홍차를 우려낸 아이스티",
                                3.4, List.of(new Option("Regular", 0),
                                                    new Option("Large", 0.5))),
                        new Menu("Fifty/Fifty",
                                "레몬에이드와 아이스티의 만남",
                                3.5, List.of(new Option("Regular", 0),
                                                    new Option("Large", 0.9)))
                ));
        menus.put(Category.BEER,
                List.of(new Menu("ShackMeister Ale",
                                "뉴욕 브루클린 브루어리에서 양조한 에일 맥주",
                                9.8),
                        new Menu("Magpie Brewing Co.",
                                "맥파이 맥주",
                                6.8)
                ));
    }

    public static List<Menu> getMenus(Category category) {
        return new ArrayList<>(menus.get(category));
    }

    public static int size() {
        return menus.size();
    }

    public static String getFormattedMenus(Category category) {
        return format(Store.getMenus(category));
    }

    private static String format(List<Menu> menus) {
        int index = 1;
        StringBuilder numbered = new StringBuilder();

        for(Menu menu: menus) {
            numbered.append(index).append(". ").append(menu.getName()).append("| W ")
                    .append(menu.getPrice()).append(" | ").append(menu.getDescription()).append("\n");
            index++;
        }
        return numbered.toString();
    }
}
