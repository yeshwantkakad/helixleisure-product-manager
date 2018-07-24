package com.helixleisure.product.productmanager.repository;

import com.helixleisure.product.productmanager.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}

