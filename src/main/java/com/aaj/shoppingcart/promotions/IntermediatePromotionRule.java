package com.aaj.shoppingcart.promotions;

import com.aaj.shoppingcart.Product;

import java.util.List;

public abstract class IntermediatePromotionRule extends PromotionRule {
    @Override
    public PromotionType getType() {
        return PromotionType.INTERMEDIATE;
    }

    public abstract Double calculateDiscount(List<Product> items);
}
