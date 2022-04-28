package kata.supermarket.pricing.discount;

import kata.supermarket.item.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountStrategy{
    BigDecimal applyDiscount(List<Item> items);

}
