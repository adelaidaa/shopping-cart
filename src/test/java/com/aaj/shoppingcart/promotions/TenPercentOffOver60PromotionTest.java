package com.aaj.shoppingcart.promotions;

import com.aaj.shoppingcart.promotions.TenPercentOffOver60Promotion;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TenPercentOffOver60PromotionTest {
    private TenPercentOffOver60Promotion tenPercentOffOver60Promotion =  new TenPercentOffOver60Promotion();

    @Test
    public void given__totalPriceUnder60__should_notApplyDiscount(){
        Double totalPriceAfterDiscount = tenPercentOffOver60Promotion.calculateDiscount(50.0);

        assertThat(totalPriceAfterDiscount, equalTo(50.0));
    }

    @Test
    public void given__totalPriceEquals60__should_apply10PerCentDiscount(){
        Double totalPriceAfterDiscount = tenPercentOffOver60Promotion.calculateDiscount(60.0);

        assertThat(totalPriceAfterDiscount, equalTo(54.0));
    }

    @Test
    public void given__totalPriceOver60__should_apply10PerCentDiscount(){
        Double totalPriceAfterDiscount = tenPercentOffOver60Promotion.calculateDiscount(89.0);

        assertThat(totalPriceAfterDiscount, equalTo(80.10));
    }
}