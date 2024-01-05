package kiosk.view;

import kiosk.data.ScreenData;
import kiosk.data.ScreenData.ScreenStage;

public class OutputView {

    private final ScreenData screenData = new ScreenData();

    private void printScreenData(ScreenStage screenStage) {
        System.out.println(screenData.getScreenData(screenStage));
    }

    public void printMainMessage() {
        System.out.println("\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"\n"
                + "아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n"
                + "\n"
                + "[ SHAKESHACK MENU ]");
        printScreenData(ScreenStage.MAIN);
    }
}
