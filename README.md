This is a short object oriented programming
test.

Online marketplace, here is a sample of some of the products available on our site:
Product code | Name | Price
----------------------------------------------------------
001 | Travel Card Holder | £9.25
002 | Personalised cufflinks | £45.00
003 | Kids T-shirt | £19.95
Our marketing team want to offer promotions as an incentive for
our customers to purchase these items.
If you spend over £60, then you get 10% off your purchase
If you buy 2 or more travel card holders then the price drops to
£8.50.
Our check-out can scan items in any order, and because our
promotions will change, it needs to be flexible regarding our
promotional rules.
The interface to our checkout looks like this (shown in Java):
Checkout co = new Checkout(promotionalRules);
co.scan(item);
co.scan(item);
Double price = co.total();
Implement a checkout system that fulfils these requirements.
Test data
---------
Basket: 001,002,003
Total price expected: £66.78
Basket: 001,003,001
Total price expected: £36.95
Basket: 001,002,001,003
Total price expected: £73.76

This is a simple implementation for the Checkout interface to be able to 
apply promotions on a Checkout scenario making easy to add new promotions 
in the future.

From the problem I have identified we could have 2 different promotion types
some applied to the final price of the whole shopping basket versus promotions
that belong to a specific product.

In that sense I have created the following abstractions in order to model this:
                    
                    PromotionRule
                     |          |
      TerminalPromotion       IntermediatePromotion
                   |                |
                   |                |
                   |                |
      FinalPricePromotion         ReducedPricePromotion
                   |                    |
    TenPercentOffOver60Promotion      TwoOrMoreTravelHoldersPromotion
      

TerminalPromotion -  affects to the total price of a shopping scenario
IntermediatePromotion - affects to specific products on the shopping basket

FinalPricePromotion -  affects to the final price of a shopping basket, and 
apply's a percentage discount

Another future terminal promotions could be added that need to affect the final price but 
could need different rules like if number of items > 10 , discount the cheapest.

TenPercentOffOver60Promotion -  this is a concrete promotion where we get 10% off for
total price shopping bigger than 60.

ReducedPricePromotion - this is an intermediate discount that is applied before the terminal
discounts. In this example we identified that at some point a specific product could be discounted
when the number of same items of the specific product is bought.

In the future we could have different Intermediate promotions where we get one for free
when we buy a certain number.

TwoOrMoreTravelHoldersPromotion -  this is the concrete class to set the parameters needed
in order to apply a discount to Travel Holders when we buy more than 2.

If we have any other products discounted in the future in the same way we just need to
extend the ReducedPricePromotion to get that type of discount.


For the purpose of this exercise we have also created the Product class that represent
the products itself, only code, name and price had been added for simplicity but might need
to be extended in the future to add currency and maybe the price needs to be calculated depending
on the currency.

Also for simplicity the product's values had been harcoded in the TwoOrMoreTravelHoldersPromotion for the 
purpose of the exercise but ideally all promotions should be stored and we should be able to
retrieve the active promotions in order to be able to apply them at checkout, 
instead of injecting the promotions on the Checkout implementation to avoid coupling
between checkout and promotions.

Also in order to get a history of purchases we should be able to store each purchase 
with all products bought, prices etc.

Tests had been added to show the implementation of the Checkout interface working.

