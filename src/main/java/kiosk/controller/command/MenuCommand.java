package kiosk.controller.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import kiosk.data.ApplicationStatus;
import kiosk.domain.Store;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;

public class MenuCommand implements Command {

    private static final List<Integer> commands = new ArrayList<>();

    private final int input;
    private final List<Menu> menus;

    private MenuCommand(int input, List<Menu> menus) {
        this.input = input;
        this.menus = menus;
    }

    public static MenuCommand of(int input, Category category) {
        List<Menu> menus = Store.getMenus(category);
        initialize(category, menus);
        validate(input);
        return new MenuCommand(input, menus);
    }

    private static void initialize(Category category, List<Menu> menus) {
        int categorySize = menus.size();
        IntStream.rangeClosed(1, categorySize)
                .forEach(commands::add);
    }

    private static void validate(int input) {
        if (!commands.contains(input)) {
            throw new IllegalArgumentException(String.format("[ERROR] 없는 메뉴 입니다. input:" + input));
        }
    }

    public Menu getChosenMenu() {
        return menus.get(input);
    }

    public ApplicationStatus status() {
        Menu chosen = getChosenMenu();
        if(chosen.hasOption()) {
            return ApplicationStatus.OPTION;
        }
        return ApplicationStatus.PURCHASE;
    }

    @Override
    public int info() {
        return input;
    }
}
