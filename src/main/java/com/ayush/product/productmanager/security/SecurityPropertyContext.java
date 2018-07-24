package com.helixleisure.product.productmanager.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A Data Holder class that will read the properties file and create a bean which can be injected
 * by autowiring it where ever security related properties are required
 */
@Component
@Getter
public class SecurityPropertyContext {

    @Value("${app.name}")
    private String appName;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expires_in}")
    private int expiresIn;

    @Value("${jwt.header}")
    private String authHeader;
}
