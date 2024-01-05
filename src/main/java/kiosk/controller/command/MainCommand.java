package kiosk.controller.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import kiosk.data.ApplicationStatus;
import kiosk.domain.Store;

public class MainCommand implements Command {

    private static final int BASIC_COMMANDS = 2;
    private static final List<Integer> commands = new ArrayList<>();

    private final int input;

    private MainCommand(int input) {
        this.input = input;
    }

    public static MainCommand of(int input) {
        initialize();
        validate(input);
        return new MainCommand(input);
    }

    private static void initialize() {
        int storeSize = Store.size();
        IntStream.rangeClosed(1, storeSize + BASIC_COMMANDS)
                .forEach(commands::add);
    }

    private static void validate(int input) {
        if (!commands.contains(input)) {
            throw new IllegalArgumentException(String.format("[ERROR]올바른 커맨드 입력이 아닙니다. input : " + input));
        }
    }

    public ApplicationStatus status() {
        if (input < Store.size()) {
            return ApplicationStatus.MENU;
        }
        if (input == Store.size() + 1) {
            return ApplicationStatus.CART;
        }
        return ApplicationStatus.EXIT;
    }

    @Override
    public int info() {
        return input;
    }
}
