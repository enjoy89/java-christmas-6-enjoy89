package christmas.model.discount;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);

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

    public static Badge getDefault() {
        return NONE;
    }
}
