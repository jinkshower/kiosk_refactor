package kiosk.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import kiosk.view.InputView;
import kiosk.view.OutputView;

public class KioskController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> application = new HashMap<>();

    public KioskController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeApplicationStatus();
    }

    private void initializeApplicationStatus() {
        application.put(ApplicationStatus.MAIN, this::mainMenu);
    }

    public ApplicationStatus mainMenu() {
        outputView.printMainMessage();
        inputView.readMenuCommand();

        return ApplicationStatus.EXIT;
    }


    // for running application until exit
    public void run() {
        ApplicationStatus applicationStatus = process(ApplicationStatus.MAIN);
        while (applicationStatus.onProcess()) {
            applicationStatus = process(applicationStatus);
        }
    }

    // for running application until exit
    private ApplicationStatus process(ApplicationStatus applicationStatus) {
        while (true) {
            try {
                return application.get(applicationStatus).get();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
