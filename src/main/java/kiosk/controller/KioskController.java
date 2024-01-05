package kiosk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import kiosk.controller.command.Command;
import kiosk.controller.command.MainCommand;
import kiosk.data.ApplicationStatus;
import kiosk.domain.menu.Category;
import kiosk.view.InputView;
import kiosk.view.OutputView;

public class KioskController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> application = new HashMap<>();
    private final List<Command> commands = new ArrayList<>();

    public KioskController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        initializeApplicationStatus();
    }

    private void initializeApplicationStatus() {
        application.put(ApplicationStatus.MAIN, this::mainScreen);
        application.put(ApplicationStatus.MENU, this::menu);
        application.put(ApplicationStatus.CART, this::cart);
    }

    public ApplicationStatus mainScreen() {
        outputView.printMainMessage();
        MainCommand mainCommand = MainCommand.of(inputView.readMainCommand());
        commands.add(mainCommand);

        return mainCommand.status();
    }

    private ApplicationStatus menu() {
        System.out.println("menu selected");
        System.out.println(Category.from(commands.get(0).info()));
        return ApplicationStatus.EXIT;
    }

    private ApplicationStatus cart() {
        System.out.println("cart selected");
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
