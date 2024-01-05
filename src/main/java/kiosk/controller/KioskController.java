package kiosk.controller;

import kiosk.view.InputView;
import kiosk.view.OutputView;

public class KioskController {

    private final InputView inputView;
    private final OutputView outputView;

    public KioskController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printMainMessage();
    }
}
