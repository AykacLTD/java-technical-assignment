package kata.supermarket;
import kata.supermarket.item.Item;
import kata.supermarket.item.impl.Product;
import kata.supermarket.item.impl.WeighedProduct;
import kata.supermarket.pricing.PricingCalculator;
import kata.supermarket.pricing.discount.impl.DiscountStrategyBuyOneGetOneFree;
import kata.supermarket.pricing.discount.impl.DiscountStrategyBuyOneKiloForHalfPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {
    public static final Item ITEM_PACK_DIGESTIVES = new Product(new BigDecimal("1.55")).oneOf();
    public static final Item ITEM_BOTTLE_MILK = new Product(new BigDecimal("0.49")).oneOf();
    public static final Item ITEM_CAN_SODA = new Product(new BigDecimal("1.10")).oneOf();
    public static final Item ITEM_APPLE = new WeighedProduct(new BigDecimal("1.20")).weighing(new BigDecimal("3.0"));
    public static final Item ITEM_SUGAR = new WeighedProduct(new BigDecimal("1.50")).weighing(new BigDecimal("2.0"));
    public static final Item ITEM_RICE = new WeighedProduct(new BigDecimal("1.90")).weighing(new BigDecimal("4.0"));
    public static final List<Item> MIXEDITEMS_LIST =  Arrays.asList(ITEM_APPLE, ITEM_RICE, ITEM_SUGAR, ITEM_CAN_SODA, ITEM_CAN_SODA, ITEM_CAN_SODA, ITEM_BOTTLE_MILK, ITEM_BOTTLE_MILK, ITEM_BOTTLE_MILK, ITEM_BOTTLE_MILK, ITEM_PACK_DIGESTIVES, ITEM_PACK_DIGESTIVES, ITEM_PACK_DIGESTIVES, ITEM_PACK_DIGESTIVES);
    public static final List<Item> ITEMSBYUNIT_LIST = Arrays.asList(ITEM_CAN_SODA, ITEM_BOTTLE_MILK, ITEM_CAN_SODA);
    public static final List<Item> ITEMSBYWEIGHT_LIST = Arrays.asList(ITEM_APPLE, ITEM_RICE, ITEM_SUGAR);
    private final DiscountStrategyBuyOneGetOneFree discountStrategyBuyOneGetOneFree = new DiscountStrategyBuyOneGetOneFree();
    private final DiscountStrategyBuyOneKiloForHalfPrice discountStrategyBuyOneKiloForHalfPrice = new DiscountStrategyBuyOneKiloForHalfPrice();

    @DisplayName("No discount  discount strategy tests")
    @MethodSource("prepareDataForNoDiscountTest")
    @ParameterizedTest(name = "{0}")
    void noDiscountTest( String description, String expectedTotal, List<Item> items) {
        Basket basket = new Basket();
        //byy default no discount strategy will be used
        PricingCalculator pricingCalculator = new PricingCalculator();
        basket.setPricingCalculator(pricingCalculator);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
    @DisplayName("ByOneGetOneFree discount strategy unit tests")
    @MethodSource("prepareDataForBuyOneGetOneFreeTest")
    @ParameterizedTest(name = "{0}")
    void buyOneGetOneFreeTest(String description, String expectedTotal, List<Item> items) {
        Basket basket = new Basket();
        //setting the pricingCalculator with matching discount strategy to test
        PricingCalculator pricingCalculator = new PricingCalculator(discountStrategyBuyOneGetOneFree);
        basket.setPricingCalculator(pricingCalculator);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
    @DisplayName("\"buyOneKiloForHalfPrice discount strategy unit tests\"")
    @MethodSource("prepareDataForBuyOneKiloForHalfPriceTest")
    @ParameterizedTest(name = "{0}")
    void prepareDataForBuyOneKiloForHalfPriceTest(String description, String expectedTotal, List<Item> items) {
        Basket basket = new Basket();
        //setting the pricingCalculator with matching discount strategy to test
        PricingCalculator pricingCalculator = new PricingCalculator(discountStrategyBuyOneKiloForHalfPrice);
        basket.setPricingCalculator(pricingCalculator);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
    static Stream<Arguments> prepareDataForNoDiscountTest(){
        return Stream.of(
                prepareArguments("Multiple itemsbyunit items","2.69", ITEMSBYUNIT_LIST),
                prepareArguments("Multiple itemsbyweight items","14.20",ITEMSBYWEIGHT_LIST),
                prepareArguments("Multiple mixed type items","25.66",MIXEDITEMS_LIST)
        );
    }
    static Stream<Arguments> prepareDataForBuyOneGetOneFreeTest(){
        return Stream.of(
                prepareArguments("Multiple itemsbyunit items", "1.59", ITEMSBYUNIT_LIST),
                prepareArguments("Multiple mixed type items", "20.48", MIXEDITEMS_LIST)
        );
    }
    static Stream<Arguments> prepareDataForBuyOneKiloForHalfPriceTest(){
        return Stream.of(
                prepareArguments("Multiple itemsbyweight items","7.10",ITEMSBYWEIGHT_LIST),
                prepareArguments("Multiple mixed type items","18.56",MIXEDITEMS_LIST)
        );
    }
    private static Arguments prepareArguments(String description, String expected, List<Item> items) {
        return Arguments.of(description, expected, items);
    }
}