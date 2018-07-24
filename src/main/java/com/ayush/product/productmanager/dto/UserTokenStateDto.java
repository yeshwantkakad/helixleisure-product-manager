package com.helixleisure.product.productmanager.dto;

import lombok.Data;

/**
 * The DTO Class Exposed to the user when token is created/refreshed
 * @author yeshwant
 */
@Data
public class UserTokenStateDto {
    private String access_token;
    private Long expires_in;

    public UserTokenStateDto() {
        this.access_token = null;
        this.expires_in = null;
    }

    public UserTokenStateDto(String access_token, long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
    }
}