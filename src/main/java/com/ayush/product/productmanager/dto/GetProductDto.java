package com.helixleisure.product.productmanager.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetProductDto {

    private String id;

    private Date timestamp;

    private List<ProductIdDto> products;
}
