package com.helixleisure.product.productmanager.service;

import com.helixleisure.product.productmanager.entity.ProductEntity;
import com.helixleisure.product.productmanager.mapper.ProductMapper;
import com.helixleisure.product.productmanager.repository.ProductDao;
import com.helixleisure.product.productmanager.dto.ProductIdDto;
import com.helixleisure.product.productmanager.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements  ProductService {

    private final ProductDao productDao;

    @Override
    public ProductEntity getProduct(long productId) {

        ProductEntity entity = productDao.findById(productId).orElse(null);
        return entity;
    }

    @Override
    public List<ProductDto> createProducts(List<ProductDto> products) {
        List<ProductEntity> convertedEntities = ProductMapper.convertproductVoListToEntityList(products);
        List<ProductEntity> entities = productDao.saveAll(convertedEntities);
        List<ProductDto> dtos = ProductMapper.convertProductEntityListToProductVoList(entities);

        return dtos;
    }

    @Override
    public List<ProductDto> getProducts(List<ProductIdDto> voIds) {
        List<Long> ids = ProductMapper.convertProductIdVoListToIdList(voIds);
        List<ProductEntity> entities = productDao.findAllById(ids);
        List<ProductDto> dtos = ProductMapper.convertProductEntityListToProductVoList(entities);
        return dtos;
    }
}
