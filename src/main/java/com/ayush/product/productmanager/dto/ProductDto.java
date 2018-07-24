package com.helixleisure.product.productmanager.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private long id;
    private String name;
    private int quantity;
    private BigDecimal sale_amount;
}
