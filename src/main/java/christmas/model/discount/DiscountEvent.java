package christmas.model.discount;

public interface DiscountEvent {
    Amount calculateTotalDiscountAmount();
    String getName();
}
