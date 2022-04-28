package kata.supermarket.pricing.discount.impl;

import kata.supermarket.item.Item;
import kata.supermarket.item.impl.ItemByUnit;
import kata.supermarket.pricing.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountStrategyBuyOneGetOneFree implements DiscountStrategy {
    private final int discountRate = 2;

    @Override
    public BigDecimal applyDiscount(List<Item> items) {
        double totalDiscount = 0.0d;
        long discountCount = 0;
        List<ItemByUnit> eligibleItems = items.stream().filter(ItemByUnit.class::isInstance).map(ItemByUnit.class::cast).collect(Collectors.toList());
        Set<ItemByUnit> uniqueList = new HashSet<>(eligibleItems);

        for (ItemByUnit item : uniqueList) {
            BigDecimal price = item.price();
            long numberOfMatchingItem = eligibleItems.stream().filter(x -> x.equals(item)).count();
            discountCount = numberOfMatchingItem / discountRate;
            totalDiscount += price.doubleValue() * discountCount;
        }
        return BigDecimal.valueOf(totalDiscount).setScale(2, RoundingMode.HALF_UP);
    }
}
