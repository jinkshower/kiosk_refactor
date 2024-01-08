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
import kiosk.data.OrderDto;
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
    private OrderDto orderDto;

    public KioskController(InputView inputView, OutputView outputView, Cart cart) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cart = cart;
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
        outputView.printMainMessage();
        MainCommand mainCommand = MainCommand.of(inputView.readCommand());
        commands.put(ApplicationStatus.MAIN, mainCommand);

        return mainCommand.status();
    }

    private ApplicationStatus menu() {
        Category chosen = Category.from(commands.get(ApplicationStatus.MAIN).info());
        orderDto = new OrderDto(chosen);

        outputView.printMenuMessage(Store.getFormattedMenus(chosen));
        MenuCommand menuCommand = MenuCommand.of(inputView.readCommand(), chosen);
        orderDto.setMenu(menuCommand.getChosenMenu());

        return menuCommand.status();
    }

    private ApplicationStatus option() {
        Menu chosen = orderDto.getMenu();
        outputView.printOptionMessage(chosen.formatted(), chosen.optionMessage());
        OptionCommand optionCommand = OptionCommand.of(inputView.readCommand(), chosen);

        Option selected = chosen.getOptions().get(optionCommand.info());
        orderDto.setOption(selected);

        return ApplicationStatus.ADD_CART;
    }

    private ApplicationStatus addCart() {
        Order order = new Order(orderDto.getMenu(), orderDto.getOption());
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
        History.add(cart.getOrders());
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
        double total = History.totalPrice();
        String history = History.formatted();
        outputView.historyMessage(total, history);

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
