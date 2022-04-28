package kata.supermarket.item.impl;

import kata.supermarket.item.Item;

import java.math.BigDecimal;

public class ItemByUnit implements Item {
    private final Product product;

    public ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }
}