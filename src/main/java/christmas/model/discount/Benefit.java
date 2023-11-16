package christmas.model.discount;

import java.util.Arrays;
import java.util.Comparator;

public class Benefit {
    private final Amount totalBenefit;

    private Benefit(Amount totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public static Benefit of(Amount totalBenefit) {
        return new Benefit(totalBenefit);
    }

    public Badge getBadge() {
        return Arrays.stream(Badge.values())
                .filter(b -> totalBenefit.amount() >= b.getMinPrice())
                .max(Comparator.comparingInt(Badge::getMinPrice))
                .orElse(Badge.getDefault());
    }

}
