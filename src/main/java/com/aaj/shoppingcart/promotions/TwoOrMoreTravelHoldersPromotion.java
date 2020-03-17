package com.aaj.shoppingcart.promotions;

public class TwoOrMoreTravelHoldersPromotion extends ReducedPricePromotionRule  {

    private static final long PRODUCT_CODE = 001L;
    private static final int ITEMS_TO_APPLY_PROMOTION = 2;
    private static final double REGULAR_PRICE = 9.25;
    private static final double NEW_PRICE = 8.50;

    public TwoOrMoreTravelHoldersPromotion() {
        super(PRODUCT_CODE, ITEMS_TO_APPLY_PROMOTION, REGULAR_PRICE, NEW_PRICE);
    }
}
