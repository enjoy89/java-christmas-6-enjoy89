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
        Menus menu = getMenuByName(menuName);
        return menu.getPrice() * quantity;
    }

    public Menus getMenuByName(String menuName) {
        for (Menus menu : Menus.values()) {
            if (menu.getName().equals(menuName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.get());
    }
}
