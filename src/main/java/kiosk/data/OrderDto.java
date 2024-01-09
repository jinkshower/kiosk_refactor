package kiosk.data;

import java.util.Optional;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;

public class OrderDto {

    private Category category;
    private Menu menu;
    private Option option;

    public OrderDto(Category category) {
        this.category = category;
        this.menu = null;
        this.option = null;
    }

    public Optional<Menu> getMenu() {
        return Optional.ofNullable(menu);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Optional<Option> getOption() {
        return Optional.ofNullable(option);
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
