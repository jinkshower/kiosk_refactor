package kiosk.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int readMainCommand() {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[ERROR]숫자만 입력할 수 있습니다." + input));
        }
    }
}
