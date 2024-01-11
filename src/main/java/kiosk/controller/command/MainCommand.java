package kiosk.controller.command;

import kiosk.data.ApplicationStatus;
import kiosk.domain.Store;

public class MainCommand implements Command {

    private static final int BASIC_COMMANDS = 2;

    private final int input;

    private MainCommand(int input) {
        this.input = input;
    }

    public static MainCommand of(int input) {
        validate(input);
        return new MainCommand(input);
    }

    private static void validate(int input) {
        int maxInputSize = Store.size() + BASIC_COMMANDS;
        if (input < 0 || input > maxInputSize) {
            throw new IllegalArgumentException(String.format("[ERROR]올바른 커맨드 입력이 아닙니다. input : " + input));
        }
    }

    public ApplicationStatus status() {
        if (input == 0) {
            return ApplicationStatus.HISTORY;
        } else if (input <= Store.size()) {
            return ApplicationStatus.MENU;
        } else if (input == Store.size() + 1) {
            return ApplicationStatus.CART;
        } else if (input == Store.size() + 2) {
            return ApplicationStatus.CANCEL;
        }
        return ApplicationStatus.EXIT;
    }

    @Override
    public int info() {
        return input;
    }
}
