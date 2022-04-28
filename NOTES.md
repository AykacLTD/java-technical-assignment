# Notes
This implementation assumptions and design decisions given below:

## Assumptions - For Test Purposes

- It is assumed that discount strategy is for a specific item type and so each discount strategy checks all items in basket and applies discount strategy for eligible ones. Eligible checks in discount strategies are implemented accordingly.
- It is assumed that a singleton instance of the item will be added to basket to have multiple units of that item in basket in real. If a new instance created for that item (with same price) and added to basket, it will be accepted as a new different item. Tests are implemented with this assumption.
- It is assumed only one discount strategy will be applied to basket for this implementation. 
- 3 different discount strategies implemented for testing
  - No discount
  - BuyOneGetOneFree
  - BuyOneKiloGetForHalfPrice
- 
## Design Decisions

- Basket is using pricingCalculator helper class to calculate total price
- Discount logic is set inside pricingCalculator helper class and applied during calculating total price. 
- Design decision is to set required discountStrategy manually but it is possible to set based on rules ( e.g. rules based items in basket) in pricingCalculator.
- 

![](/Users/alperaykac/IdeaProjects/java-technical-assignment/src/main/resources/Basket.png "Basket Class Diagram")

![](/Users/alperaykac/IdeaProjects/java-technical-assignment/src/main/resources/DiscountStrategy.png "Discount Strategy Class Diagram")