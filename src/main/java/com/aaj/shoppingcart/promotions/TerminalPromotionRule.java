package com.aaj.shoppingcart.promotions;

public abstract class TerminalPromotionRule extends PromotionRule {
    @Override
    public PromotionType getType() {
        return PromotionType.TERMINAL;
    }

    public abstract Double calculateDiscount(Double totalPrice);
}
