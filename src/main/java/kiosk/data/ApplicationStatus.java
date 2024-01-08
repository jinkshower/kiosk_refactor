package kiosk.data;

public enum ApplicationStatus {
    MAIN,
    MENU,
    OPTION,
    ADD_CART,
    PURCHASE,
    CART,
    CANCEL,
    EXIT,
    HISTORY;

    public boolean onProcess() {
        return this != EXIT;
    }
}
