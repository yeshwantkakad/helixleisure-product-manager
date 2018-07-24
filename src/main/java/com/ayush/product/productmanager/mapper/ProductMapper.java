package com.helixleisure.product.productmanager.mapper;

import com.helixleisure.product.productmanager.entity.ProductEntity;
import com.helixleisure.product.productmanager.dto.ProductIdDto;
import com.helixleisure.product.productmanager.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity convertProductVoToEntity(ProductDto vo){
        ProductEntity entity = new ProductEntity();
        entity.setId(vo.getId());
        entity.setName(vo.getName());
        entity.setQuantity(vo.getQuantity());
        entity.setSaleAmount(vo.getSale_amount());
        return entity;
    }

    public static List<ProductEntity> convertproductVoListToEntityList(List<ProductDto> voList){
        List<ProductEntity> entities = voList.stream().map(vo -> convertProductVoToEntity(vo))
                .collect(Collectors.toList());
        return entities;
    }

    public static ProductDto convertProductEntityToProductVo(ProductEntity entity){
        ProductDto vo = new ProductDto();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setQuantity(entity.getQuantity());
        vo.setSale_amount(entity.getSaleAmount());
        return vo;
    }
    public static List<ProductDto> convertProductEntityListToProductVoList(List<ProductEntity> entities){
        List<ProductDto> dtos = entities.stream().map(entity->convertProductEntityToProductVo(entity))
                .collect(Collectors.toList());
        return dtos;
    }

    public static List<Long> convertProductIdVoListToIdList(List<ProductIdDto> vos){
        List<Long> ids = vos.stream().map(vo -> vo.getId()).collect(Collectors.toList());
        return ids;
    }
}
