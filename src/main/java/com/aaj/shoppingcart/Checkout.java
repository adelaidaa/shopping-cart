package com.aaj.shoppingcart;

import com.aaj.shoppingcart.promotions.PromotionRule;
import com.aaj.shoppingcart.promotions.IntermediatePromotionRule;
import com.aaj.shoppingcart.promotions.TerminalPromotionRule;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@EqualsAndHashCode
public class Checkout {
    private final List<PromotionRule> promotionRules;
    private final List<Product> items;

    public Checkout(List<PromotionRule> promotionRules) {
        this.promotionRules = promotionRules;
        this.items = Collections.synchronizedList(new ArrayList<>());
    }

    public void scan(Product item) {
        items.add(item);
    }

    public Double total() {
        synchronized(items) {
            Map<PromotionRule.PromotionType, List<PromotionRule>> promotionsByType =
                    promotionRules.stream()
                            .collect(groupingBy(PromotionRule::getType));

            double totalPriceWithIntermediateDiscounts = getTotalPriceDeductingIntermediateDiscounts(promotionsByType);
            return getTotalPriceDeductingTerminalDiscounts(promotionsByType, totalPriceWithIntermediateDiscounts);
        }
    }

    private double getTotalPriceDeductingIntermediateDiscounts(Map<PromotionRule.PromotionType, List<PromotionRule>> promotionsByType) {
        double intermediateDiscountsSum = promotionsByType.get(PromotionRule.PromotionType.INTERMEDIATE)
                .stream()
                .mapToDouble(promotionRule -> ((IntermediatePromotionRule) promotionRule).calculateDiscount(items))
                .sum();

        return items
                .stream()
                .mapToDouble(item -> item.getPrice())
                .sum() - intermediateDiscountsSum;
    }

    private double getTotalPriceDeductingTerminalDiscounts(Map<PromotionRule.PromotionType, List<PromotionRule>> promotionsByType,
                                                           double totalPrice) {
        List<PromotionRule> terminalPromotionRules = promotionsByType.get(PromotionRule.PromotionType.TERMINAL);
        for (PromotionRule rule : terminalPromotionRules) {
            totalPrice = ((TerminalPromotionRule) rule).calculateDiscount(totalPrice);
        }
        return totalPrice;
    }
}
