package kiosk.controller.command;

public class AddCartCommand implements Command {

    private final int input;

    private AddCartCommand(int input) {
        this.input = input;
    }

    public static AddCartCommand of(int input) {
        validate(input);
        return new AddCartCommand(input);
    }

    private static void validate(int input) {
        if (input != 1 && input != 2) {
            throw new IllegalArgumentException(String.format("[ERROR] 없는 명령어 입니다. input:" + input));
        }
    }

    public boolean granted() {
        return input == 1;
    }

    @Override
    public int info() {
        return input;
    }
}
