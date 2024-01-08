package kiosk.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kiosk.domain.menu.Category;

public class ScreenData {

    private static final int INITIAL_MAIN_INDEX = 1;

    private final Map<ApplicationStatus, String> screenData = new HashMap<>();

    public ScreenData() {
        initialize();
    }

    public String getScreenData(ApplicationStatus applicationStatus) {
        return screenData.get(applicationStatus);
    }

    private void initialize() {
        screenData.put(ApplicationStatus.MAIN, numberedMessage(parseCategoryMessage(Category.values())) + "\n" +
                "[ ORDER MENU ]\n" +
                "5. Order       | 장바구니를 확인 후 주문합니다.\n" +
                "6. Cancel      | 진행중인 주문을 취소합니다.");
    }

    private String numberedMessage(List<String> strings) {
        int index = INITIAL_MAIN_INDEX;
        StringBuilder numbered = new StringBuilder();

        for (String string : strings) {
            numbered.append(index).append(". ").append(string).append("\n");
            index++;
        }
        return numbered.toString();
    }

    private List<String> parseCategoryMessage(Category[] categories) {
        List<String> categoryMessages = new ArrayList<>();

        for (Category category : categories) {
            String categoryMessage = category.getMessage();
            categoryMessages.add(categoryMessage);
        }

        return categoryMessages;
    }
}
