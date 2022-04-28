package kata.supermarket.pricing;

import kata.supermarket.item.Item;
import kata.supermarket.pricing.discount.DiscountStrategy;
import kata.supermarket.pricing.discount.impl.DiscountStrategyNoDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PricingCalculator {
    private final DiscountStrategy discount;

    public PricingCalculator() {
        this.discount = new DiscountStrategyNoDiscount();
    }

    public PricingCalculator(DiscountStrategy discount) {
        this.discount = discount;
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
        totalDiscount = totalDiscount.add(discount.applyDiscount(items))
                .setScale(2, RoundingMode.HALF_UP);
        return totalDiscount;
    }

    public BigDecimal calculate(List<Item> items) {
        return subtotal(items).subtract(discounts(items));
    }
}
