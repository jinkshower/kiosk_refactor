package kiosk.controller.command;

import kiosk.domain.menu.Menu;

public class OptionCommand implements Command {

    private static final int MIN_COMMAND = 1;

    private final int input;

    private OptionCommand(int input) {
        this.input = input;
    }

    public static OptionCommand of(int input, Menu menu) {
        validate(input, menu);
        return new OptionCommand(input);
    }

    private static void validate(int input, Menu menu) {
        if (input < MIN_COMMAND || input > menu.optionsCount()) {
            throw new IllegalArgumentException(String.format("[ERROR] 없는 옵션 입니다. input:" + input));
        }
    }

    @Override
    public int info() {
        return input - 1;
    }
}
