package kiosk.view;

import kiosk.data.ApplicationStatus;
import kiosk.data.ScreenData;

public class OutputView {

    private final ScreenData screenData = new ScreenData();

    private void printScreenData(ApplicationStatus applicationStatus) {
        System.out.println(screenData.getScreenData(applicationStatus));
    }

    public void printMainMessage() {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"\n"
                + "아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n"
                + "\n"
                + "[ SHAKESHACK MENU ]");
        printScreenData(ApplicationStatus.MAIN);
    }

    public void printMenuMessage(String message) {
        System.out.println(" \"SHAKESHACK BURGER 에 오신걸 환영합니다.\"\n"
                + "아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.");
        System.out.println(message);
    }
}
