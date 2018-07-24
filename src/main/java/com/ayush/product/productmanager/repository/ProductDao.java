package com.helixleisure.product.productmanager.repository;

import com.helixleisure.product.productmanager.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<ProductEntity, Long> {
}
