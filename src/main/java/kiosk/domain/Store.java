package kiosk.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;

public class Store {

    public static final Map<Category, List<Menu>> menus = new EnumMap<>(Category.class);

    static {
        menus.put(Category.BURGER,
                List.of(new Menu.Builder("ShackBurger", 6.9)
                                .description("토마토, 양상추, 쉑소스가 토핑된 치즈버거")
                                .options(List.of(new Option("Single", 0),
                                        new Option("Double", 3.6)))
                                .build(),
                        new Menu.Builder("SmokeShakck", 8.9)
                                .description("토마토, 양상추, 쉑소스가 토핑된 치즈버거")
                                .options(List.of(new Option("Single", 0),
                                        new Option("Double", 3.6)))
                                .build(),
                        new Menu.Builder("Shroom Burger", 9.4)
                                .description("몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거")
                                .options(List.of(new Option("Single", 0),
                                        new Option("Double", 3.6)))
                                .build(),
                        new Menu.Builder("Cheeseburger", 6.9)
                                .description("포테이토 번과 비프패티, 치즈가 토핑된 치즈버거")
                                .options(List.of(new Option("Single", 0),
                                        new Option("Double", 3.6)))
                                .build()
                ));
        menus.put(Category.FROZEN_CUSTARD,
                List.of(new Menu.Builder("Shake of the Week", 6.5)
                                .description("특별한 커스터드 플레이버")
                                .build(),
                        new Menu.Builder("Red Bean Shake", 6.5)
                                .description("신선한 커스터드와 함께 우유와 레드빈이 블렌딩 된 시즈널 쉐이크")
                                .build(),
                        new Menu.Builder("Floats", 5.9)
                                .description("루트 비어, 퍼플 카우, 크림시클")
                                .build()
                ));
        menus.put(Category.DRINK,
                List.of(new Menu.Builder("Shack-made Lemonade", 3.9)
                                .description("매장에서 직접 만드는 상큼한 레몬에이드")
                                .options(List.of(new Option("Regular", 0),
                                        new Option("Large", 0.6)))
                                .build(),
                        new Menu.Builder("Fresh Brewed Iced Tea", 3.4)
                                .description("직접 유기농 홍차를 우려낸 아이스티")
                                .options(List.of(new Option("Regular", 0),
                                        new Option("Large", 0.5)))
                                .build(),
                        new Menu.Builder("Fifty/Fifty", 3.5)
                                .description("레몬에이드와 아이스티의 만남")
                                .options(List.of(new Option("Regular", 0),
                                                new Option("Large", 0.9)))
                                .build()
                ));
        menus.put(Category.BEER,
                List.of(
                        new Menu.Builder("ShackMeister Ale", 9.8)
                                .description("뉴욕 브루클린 브루어리에서 양조한 에일 맥주")
                                .build(),
                        new Menu.Builder("Magpie Brewing Co.", 6.8)
                                .description("맥파이 맥주")
                                .build()
                ));
    }

    public static List<Menu> getMenus(Category category) {
        return new ArrayList<>(menus.get(category));
    }

    public static int size() {
        return menus.size();
    }

    public static int longestNameLength() {
        return menus.keySet().stream()
                .flatMap(category -> getMenus(category).stream())
                .mapToInt(menu -> menu.getName().length())
                .max()
                .orElse(0);
    }

    public static String getFormattedMenus(Category category) {
        return format(Store.getMenus(category));
    }

    private static String format(List<Menu> menus) {
        int index = 1;
        StringBuilder numbered = new StringBuilder();

        for (Menu menu : menus) {
            numbered.append(index).append(". ").append(menu.paddedName()).append("| W ")
                    .append(menu.getPrice()).append(" | ").append(menu.getDescription()).append("\n");
            index++;
        }
        return numbered.toString();
    }
}
