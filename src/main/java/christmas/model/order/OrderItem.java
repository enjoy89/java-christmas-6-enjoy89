package christmas.model.order;

import christmas.common.ErrorMessage;

public class OrderItem {
    private final String menuName;
    private final int quantity;

    public OrderItem(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculateTotalPrice() {
        Menu menu = getMenuByName(menuName);
        return menu.getPrice() * quantity;
    }

    public MenuCategory getCategory() {
        Menu menu = getMenuByName(menuName);
        return menu.getCategory();
    }

    public Menu getMenuByOrderItem() {
        return getMenuByName(getMenuName());
    }

    private Menu getMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
    }
}
