package kiosk.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import kiosk.controller.command.BasicCommand;
import kiosk.controller.command.Command;
import kiosk.controller.command.MainCommand;
import kiosk.controller.command.MenuCommand;
import kiosk.controller.command.OptionCommand;
import kiosk.controller.command.ReturnCommand;
import kiosk.data.ApplicationStatus;
import kiosk.data.OrderInformation;
import kiosk.domain.Cart;
import kiosk.domain.History;
import kiosk.domain.Order;
import kiosk.domain.Store;
import kiosk.domain.menu.Category;
import kiosk.domain.menu.Menu;
import kiosk.domain.menu.Option;
import kiosk.view.InputView;
import kiosk.view.OutputView;

public class KioskController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<ApplicationStatus, Supplier<ApplicationStatus>> application = new HashMap<>();
    private final Map<ApplicationStatus, Command> commands = new HashMap<>();
    private final Cart cart;
    private final History history;
    private OrderInformation orderInformation;

    public KioskController(InputView inputView, OutputView outputView, Cart cart, History history) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cart = cart;
        this.history = history;
        initializeApplicationStatus();
    }

    private void initializeApplicationStatus() {
        application.put(ApplicationStatus.MAIN, this::mainScreen);
        application.put(ApplicationStatus.MENU, this::menu);
        application.put(ApplicationStatus.OPTION, this::option);
        application.put(ApplicationStatus.ADD_CART, this::addCart);
        application.put(ApplicationStatus.CART, this::cart);
        application.put(ApplicationStatus.PURCHASE, this::purchase);
        application.put(ApplicationStatus.CANCEL, this::cancel);
        application.put(ApplicationStatus.HISTORY, this::history);
    }

    public ApplicationStatus mainScreen() {
        outputView.printMainMessage(Category.formatted());
        MainCommand mainCommand = MainCommand.of(inputView.readCommand());
        commands.put(ApplicationStatus.MAIN, mainCommand);

        return mainCommand.status();
    }

    private ApplicationStatus menu() {
        Category chosen = Category.from(commands.get(ApplicationStatus.MAIN).info());
        orderInformation = new OrderInformation(chosen);

        outputView.printMenuMessage(Store.getFormattedMenus(chosen));
        MenuCommand menuCommand = MenuCommand.of(inputView.readCommand(), chosen);
        orderInformation.setMenu(menuCommand.getChosenMenu());

        return menuCommand.status();
    }

    private ApplicationStatus option() {
        Menu chosen = orderInformation.getMenu().orElseThrow();
        outputView.printOptionMessage(chosen.formatted(), chosen.optionMessage());
        OptionCommand optionCommand = OptionCommand.of(inputView.readCommand(), chosen);

        Option selected = chosen.getOptions().get(optionCommand.info());
        orderInformation.setOption(selected);

        return ApplicationStatus.ADD_CART;
    }

    private ApplicationStatus addCart() {
        Order order = new Order(orderInformation);
        outputView.printPurchaseMessage(order.formatted());

        BasicCommand basicCommand = BasicCommand.of(inputView.readCommand());
        if (basicCommand.granted()) {
            cart.addOrder(order);
            outputView.printAddedMessage(order.getName());
        }

        return ApplicationStatus.MAIN;
    }

    private ApplicationStatus cart() {
        outputView.printCartMessage(cart.formatted(), cart.calculateTotalPrice());
        BasicCommand basicCommand = BasicCommand.of(inputView.readCommand());

        if (basicCommand.granted()) {
            return ApplicationStatus.PURCHASE;
        }
        return ApplicationStatus.MAIN;
    }

    private ApplicationStatus purchase() {
        outputView.printCompletionMessage();
        history.add(cart.getOrders());
        cart.clear();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }

        return ApplicationStatus.MAIN;
    }

    private ApplicationStatus cancel() {
        outputView.printCancelMessage();
        BasicCommand basicCommand = BasicCommand.of(inputView.readCommand());

        if (basicCommand.granted()) {
            cart.clear();
            outputView.cancelCompletionMessage();
        }
        return ApplicationStatus.MAIN;
    }

    private ApplicationStatus history() {
        double total = history.totalPrice();
        String historyMessage = history.formatted();
        outputView.historyMessage(total, historyMessage);

        ReturnCommand returnCommand = ReturnCommand.of(inputView.readCommand());

        return ApplicationStatus.MAIN;
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
