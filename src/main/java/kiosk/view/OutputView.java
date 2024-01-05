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

    public void printOptionMessage(String message, String option) {
        System.out.println(message);
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.println(option);
    }

    public void printPurchaseMessage(String message) {
        System.out.println(message);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n"
                + "1. 확인        2. 취소");
    }

    public void printAddedMessage(String name) {
        System.out.println(name + " 가 장바구니에 추가되었습니다.\n");
    }

    public void printCartMessage(String cart, double total) {
        System.out.println("아래와 같이 주문 하시겠습니까?\n"
                + "\n"
                + "[ Orders ]");
        System.out.println(cart);
        System.out.println("[ Total ]");
        System.out.println("W " + total);
        System.out.println("\n"
                + "1. 주문      2. 메뉴판");
    }

    public void printCompletionMessage() {
        System.out.println("주문이 완료되었습니다!\n"
                + "\n"
                + "대기번호는 [ 1 ] 번 입니다.\n"
                + "(3초후 메뉴판으로 돌아갑니다.)");
    }
}
