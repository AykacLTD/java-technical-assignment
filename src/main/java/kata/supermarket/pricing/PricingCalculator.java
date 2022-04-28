package kata.supermarket.pricing;

import kata.supermarket.item.Item;
import kata.supermarket.pricing.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PricingCalculator {
    private List<DiscountStrategy> discounts;

    public PricingCalculator() {
        this.discounts = new ArrayList<>();
    }
    public PricingCalculator(DiscountStrategy discount) {
        this.discounts = new ArrayList<>();
        this.discounts.add(discount);
    }

    public void addDiscountStrategy(DiscountStrategy discount) {
        this.discounts.add(discount);
    }

    private BigDecimal subtotal(List<Item> items) {
        return items.stream().map(Item::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * TODO: This could be a good place to apply the results of
     *  the discount calculations.
     *  It is not likely to be the best place to do those calculations.
     *  Think about how Basket could interact with something
     *  which provides that functionality.
     */
    private BigDecimal discounts(List<Item> items) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (DiscountStrategy discountStrategy : discounts) {
            totalDiscount = totalDiscount.add(discountStrategy.applyDiscount(items))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return totalDiscount;
    }

    public BigDecimal calculate(List<Item> items) {
        return subtotal(items).subtract(discounts(items));
    }
}
