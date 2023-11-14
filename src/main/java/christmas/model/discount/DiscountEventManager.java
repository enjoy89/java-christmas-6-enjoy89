package christmas.model.discount;

import christmas.model.order.OrderDate;
import christmas.model.order.OrderInformation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountEventManager {
    private final List<DiscountEvent> discountEvents;
    private final Map<String, Integer> appliedDiscountEvents;

    public DiscountEventManager(OrderDate orderDate, OrderInformation orderInformation) {
        this.discountEvents = new ArrayList<>();
        this.discountEvents.add(ChristmasDiscountEvent.of(orderDate));
        this.discountEvents.add(WeekdayDiscountEvent.of(orderDate, orderInformation));
        this.discountEvents.add(WeekendDiscountEvent.of(orderDate, orderInformation));
        this.discountEvents.add(SpecialDiscountEvent.of(orderDate));
        this.appliedDiscountEvents = new HashMap<>();
    }

    public Amount applyDiscount() {
        int totalDiscount = 0;

        for (DiscountEvent discountEvent : discountEvents) {
            Amount discountAmount = discountEvent.calculateTotalDiscountAmount();
            if (discountAmount.amount() > 0) {
                appliedDiscountEvents.put(discountEvent.getName(), discountAmount.amount());
            }
            totalDiscount += discountAmount.amount();
        }
        return new Amount(totalDiscount);
    }

    public Map<String, Integer> getAppliedDiscountEvents() {
        return appliedDiscountEvents;
    }

    public Amount getTotalDiscountAmount() {
        int totalDiscount = 0;
        applyDiscount();

        for (Map.Entry<String, Integer> entry : appliedDiscountEvents.entrySet()) {
            totalDiscount += entry.getValue();
        }
        return new Amount(totalDiscount);
    }
}
