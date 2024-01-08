package kiosk.controller.command;

public class ReturnCommand implements Command {

    private static final int RETURN = 1;

    private final int input;

    private ReturnCommand(int input) {
        this.input = input;
    }

    public static ReturnCommand of(int input) {
        validate(input);
        return new ReturnCommand(input);
    }

    private static void validate(int input) {
        if (input != RETURN) {
            throw new IllegalArgumentException(String.format("[ERROR] 없는 명령어 입니다. input:" + input));
        }
    }

    @Override
    public int info() {
        return input;
    }
}
