package com.helixleisure.product.productmanager.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PutProductDto {

    private String id;

    private Date timestamp;

    private List<ProductDto> products;
}
