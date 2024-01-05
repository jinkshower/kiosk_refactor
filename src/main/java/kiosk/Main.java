package kiosk;

import kiosk.controller.KioskController;
import kiosk.domain.Cart;
import kiosk.view.InputView;
import kiosk.view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Cart cart = new Cart();

        KioskController kioskController = new KioskController(inputView, outputView, cart);
        kioskController.run();
    }
}
