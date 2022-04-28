package kata.supermarket;

import kata.supermarket.item.Item;
import kata.supermarket.pricing.PricingCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private final List<Item> items;

    //totalCalculator knows the discountStrategy
    private PricingCalculator pricingCalculator;

    public Basket() {
        this.items = new ArrayList<>();
        // default discount strategy is no discount
        this.pricingCalculator = new PricingCalculator();
    }

    public void setPricingCalculator(PricingCalculator pricingCalculator) {
        this.pricingCalculator = pricingCalculator;
    }
// set discount strategy you need to apply with calculator

    public void add(final Item item) {
        this.items.add(item);
    }

    public BigDecimal total() {
        return pricingCalculator.calculate(items);
    }

}
