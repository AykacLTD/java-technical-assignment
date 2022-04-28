package kata.supermarket.pricing.discount.impl;

import kata.supermarket.item.Item;
import kata.supermarket.pricing.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.util.List;

public class DiscountStrategyNoDiscount implements DiscountStrategy {

    @Override
    public BigDecimal applyDiscount(List<Item> items) {
        return BigDecimal.ZERO;
    }
}
