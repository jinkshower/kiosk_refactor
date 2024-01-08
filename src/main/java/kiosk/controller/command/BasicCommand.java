package kiosk.controller.command;

public class BasicCommand implements Command {

    private static final int CONFIRM = 1;
    private static final int CANCEL = 2;

    private final int input;

    private BasicCommand(int input) {
        this.input = input;
    }

    public static BasicCommand of(int input) {
        validate(input);
        return new BasicCommand(input);
    }

    private static void validate(int input) {
        if (input != CONFIRM && input != CANCEL) {
            throw new IllegalArgumentException(String.format("[ERROR] 없는 명령어 입니다. input:" + input));
        }
    }

    public boolean granted() {
        return input == CONFIRM;
    }

    @Override
    public int info() {
        return input;
    }
}
