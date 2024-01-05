package kiosk.data;

import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;

public class OrderDto {

    private Category category;
    private Menu menu;
    private Option option;

    public OrderDto(Category category) {
        this.category = category;
        this.menu = new Menu("", 0);
        this.option = new Option("", 0);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
