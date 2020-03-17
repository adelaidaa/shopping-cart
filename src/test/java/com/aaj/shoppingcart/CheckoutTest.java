package com.aaj.shoppingcart;

import com.aaj.shoppingcart.promotions.PromotionRule;
import com.aaj.shoppingcart.promotions.TenPercentOffOver60Promotion;
import com.aaj.shoppingcart.promotions.TwoOrMoreTravelHoldersPromotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CheckoutTest {
    private List<PromotionRule> promotionRules = new ArrayList<>();
    private Product product1;
    private Product product2;
    private Product product3;

    private Checkout co;

    @BeforeEach
    public void setup(){
        promotionRules.add(new TwoOrMoreTravelHoldersPromotion());
        promotionRules.add(new TenPercentOffOver60Promotion());

        product1 = Product.builder().code(001).name("Travel Card Holder").price(9.25).build();
        product2 = Product.builder().code(002).name("Personalised cufflinks").price(45.00).build();
        product3 = Product.builder().code(003).name("Kids T-shirt").price(19.95).build();
    }

    @Test
    public void given__products1_2_3__should__return_66_point_78(){
        co = new Checkout(promotionRules);
        co.scan(product1);
        co.scan(product2);
        co.scan(product3);

        Double total = co.total();

        assertThat(total, equalTo(66.78));
    }

    @Test
    public void given__products1_3_1__should__return_36_point_95(){
        co = new Checkout(promotionRules);
        co.scan(product1);
        co.scan(product3);
        co.scan(product1);

        Double total = co.total();

        assertThat(total, equalTo(36.95));
    }

    @Test
    public void given__products1_2_1_3__should__return_73_point_76(){
        co = new Checkout(promotionRules);
        co.scan(product1);
        co.scan(product2);
        co.scan(product1);
        co.scan(product3);

        Double total = co.total();

        assertThat(total, equalTo(73.76));
    }
}