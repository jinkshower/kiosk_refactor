package kiosk.controller;

public enum ApplicationStatus {
    MAIN,
    MENU,
    PURCHASE,
    OPTION,
    CART,
    EXIT;

    public boolean onProcess() {
        return this != EXIT;
    }
}
