package com.helixleisure.product.productmanager.service;

import com.helixleisure.product.productmanager.entity.ProductEntity;
import com.helixleisure.product.productmanager.dto.ProductIdDto;
import com.helixleisure.product.productmanager.dto.ProductDto;

import java.util.List;

/**
 * Interface for Product CRUD
 *
 * @author yeshwant
 */
public interface ProductService {

    /**
     * Given a Product Id Find the respective product Details
     * @param productId
     * @return
     */
    ProductEntity getProduct(long productId);

    /**
     * Create Products in the database, if it doesnot exist. If it exist, then this method will update it
     * @param products
     * @return
     */
    List<ProductDto> createProducts(List<ProductDto> products);

    /**
     * Given the list of ids of the product, Fetch the information for each of them.
     * NOTE: If Some product Id doesnot exist. The method will simply ignore it.
     * @param ids
     * @return
     */
    List<ProductDto> getProducts(List<ProductIdDto> ids);
}
