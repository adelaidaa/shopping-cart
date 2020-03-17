package com.aaj.shoppingcart.promotions;

import com.aaj.shoppingcart.Product;

import java.util.List;

public abstract class ReducedPricePromotionRule extends IntermediatePromotionRule {
    private final Long productCode;
    private final double minTotalItemsToApplyPromotion;
    private final double regularPrice;
    private final double newPrice;

    protected ReducedPricePromotionRule(final Long productCode,
                                        final double minTotalItemsToApplyPromotion,
                                        double regularPrice, final double newPrice) {
        this.productCode = productCode;
        this.minTotalItemsToApplyPromotion = minTotalItemsToApplyPromotion;
        this.regularPrice = regularPrice;
        this.newPrice = newPrice;
    }

    @Override
    public Double calculateDiscount(List<Product> products) {
        long productCodeCount = products.stream()
                .filter(p -> p.getCode() == productCode)
                .count();

        return productCodeCount >= minTotalItemsToApplyPromotion ? ((regularPrice - newPrice) * productCodeCount) : 0.0;
    }
}
