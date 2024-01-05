package kiosk.controller.command;

public class BasicCommand implements Command {

    private final int input;

    private BasicCommand(int input) {
        this.input = input;
    }

    public static BasicCommand of(int input) {
        validate(input);
        return new BasicCommand(input);
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
