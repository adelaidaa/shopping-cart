package com.aaj.shoppingcart.promotions;

import com.aaj.shoppingcart.Product;
import com.aaj.shoppingcart.promotions.TwoOrMoreTravelHoldersPromotion;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class TwoOrMoreTravelHoldersPromotionTest {

    private static final Product travelHolder1 =  Product.builder().code(001).name("Travel Card Holder").price(9.25).build();
    private static final Product anotherProduct =  Product.builder().code(003).name("Kids T-shirt").price(19.95).build();

    private TwoOrMoreTravelHoldersPromotion travelHoldersPromotion = new TwoOrMoreTravelHoldersPromotion();

    @Test
    public void given__oneTravelHolder__should_not_apply_any_discount(){
        Double totalDiscount = travelHoldersPromotion.calculateDiscount(Arrays.asList(travelHolder1, anotherProduct));
        assertThat(totalDiscount, equalTo(0.0));
    }

    @Test
    public void given__twoTravelHolder__should_apply_two_point_twenty_five_discount(){
        Double totalDiscount = travelHoldersPromotion.calculateDiscount(Arrays.asList(travelHolder1, anotherProduct, travelHolder1, travelHolder1));
        assertThat(totalDiscount, equalTo(2.25));
    }

    @Test
    public void given__threeTravelHolder__should_apply_three_discount(){
        Double totalDiscount = travelHoldersPromotion.calculateDiscount(Arrays.asList(travelHolder1, anotherProduct,
                travelHolder1, travelHolder1, travelHolder1));
        assertThat(totalDiscount, equalTo(3.0));
    }

    @Test
    public void given__fourTravelHolder__should_apply_three_discount(){
        Double totalDiscount = travelHoldersPromotion.calculateDiscount(Arrays.asList(travelHolder1, anotherProduct,
                                                                        travelHolder1, travelHolder1, travelHolder1));
        assertThat(totalDiscount, equalTo(3.0));
    }

}