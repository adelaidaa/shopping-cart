package com.aaj.shoppingcart;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Product {
    private long code;
    private String name;
    private Double price;
}
