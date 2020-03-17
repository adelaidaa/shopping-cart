package com.aaj.shoppingcart.promotions;

public abstract class PromotionRule {
    public enum PromotionType {
        INTERMEDIATE, TERMINAL;
    }
    public abstract PromotionType getType();
}
