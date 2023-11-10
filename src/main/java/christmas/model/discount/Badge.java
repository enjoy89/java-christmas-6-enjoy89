package christmas.model.discount;

public enum Badge {

    START("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);


    private String name;
    private int minPrice;

    Badge(String name, int minPrice) {
        this.name = name;
        this.minPrice = minPrice;
    }

    public String getName() {
        return name;
    }

    public int getMinPrice() {
        return minPrice;
    }
}
