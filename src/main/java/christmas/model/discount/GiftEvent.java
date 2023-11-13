package christmas.model.discount;

import christmas.common.DiscountEventName;
import christmas.model.order.Menu;

public class GiftEvent implements DiscountEvent {

    public static GiftEvent of() {
        return new GiftEvent();
    }

    @Override
    public Amount calculateTotalDiscountAmount() {
        return new Amount(getGiftMenu());
    }

    private int getGiftMenu() {
        return Menu.DRINK_3.getPrice();
    }

    @Override
    public String getName() {
        return DiscountEventName.GIFT_EVENT.get();
    }
}
