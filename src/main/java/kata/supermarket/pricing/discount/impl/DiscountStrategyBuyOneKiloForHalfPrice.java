package kata.supermarket.pricing.discount.impl;

import kata.supermarket.item.Item;
import kata.supermarket.item.impl.ItemByWeight;
import kata.supermarket.pricing.discount.DiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DiscountStrategyBuyOneKiloForHalfPrice implements DiscountStrategy {
    private final double discountRate = 0.5d;
    @Override
    public BigDecimal applyDiscount(List<Item> items) {

        double totalDiscount = 0.0d;
        List<ItemByWeight> eligibleItems = items.stream().filter((ItemByWeight.class::isInstance)).map(ItemByWeight.class::cast).collect(Collectors.toList());
        Set<ItemByWeight> uniqueList = new HashSet<>(eligibleItems);

        for (ItemByWeight item : uniqueList) {
            BigDecimal price = item.price();
            long numberOfMatchingItem = eligibleItems.stream().filter(x -> x.equals(item)).count();
            totalDiscount += price.doubleValue() * numberOfMatchingItem * discountRate;
        }
        return BigDecimal.valueOf(totalDiscount).setScale(2, RoundingMode.HALF_UP);
    }
}
