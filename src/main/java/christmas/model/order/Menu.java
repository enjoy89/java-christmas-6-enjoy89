package christmas.model.order;

public enum Menu {

    // 에피타이저
    APPETIZER_1(MenuCategory.APPETIZER, "양송이수프", 6_000),
    APPETIZER_2(MenuCategory.APPETIZER, "타파스", 5_500),
    APPETIZER_3(MenuCategory.APPETIZER, "시저샐러드", 8_000),

    // 메인 메뉴
    MAIN_1(MenuCategory.MAIN, "티본스테이크", 55_000),
    MAIN_2(MenuCategory.MAIN, "바비큐립", 54_000),
    MAIN_3(MenuCategory.MAIN, "해산물파스타", 35_000),
    MAIN_4(MenuCategory.MAIN, "크리스마스파스타", 25_000),

    // 디저트
    DESSERT_1(MenuCategory.DESSERT, "초코케이크", 15_000),
    DESSERT_2(MenuCategory.DESSERT, "아이스크림", 5_000),

    // 음료
    DRINK_1(MenuCategory.DRINK, "제로콜라", 3_000),
    DRINK_2(MenuCategory.DRINK, "레드와인", 60_000),
    DRINK_3(MenuCategory.DRINK, "샴페인", 25_000);

    private final MenuCategory category;
    private final String name;
    private final int price;

    Menu(MenuCategory category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public MenuCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
