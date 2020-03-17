package com.aaj.shoppingcart.promotions;

public abstract class FinalPricePromotionRule extends TerminalPromotionRule {
    private static final int HUNDRED = 100;
    private final double minTotalPriceToApplyPromotion;
    private final double promotionPercentage;

    protected FinalPricePromotionRule(double minTotalPriceToApplyPromotion, double promotionPercentage) {
        this.minTotalPriceToApplyPromotion = minTotalPriceToApplyPromotion;
        this.promotionPercentage = promotionPercentage;
    }

    @Override
    public Double calculateDiscount(Double totalPrice) {
        double scale = Math.pow(10, 2);
        return totalPrice >= minTotalPriceToApplyPromotion ?
                Math.round(totalPrice * ((HUNDRED - promotionPercentage) / HUNDRED) * scale) / scale :
                totalPrice;
    }
}
