package com.helixleisure.product.productmanager.security;


import com.helixleisure.product.productmanager.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

/**
 * A helper class that provides various utility functions that are related to
 * tokens
 * @author yeshwant
 */
@Component
public class TokenHelper {

    @Autowired
    private SecurityPropertyContext securityProperty;

    static final String AUDIENCE_UNKNOWN = "unknown";
    public static final String BEARER = "Bearer ";


    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String generateToken(String username) {
        String audience = generateAudience();
        return Jwts.builder()
                .setIssuer( securityProperty.getAppName() )
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith( SIGNATURE_ALGORITHM, securityProperty.getSecret() )
                .compact();
    }

    private String generateAudience() {
        String audience = AUDIENCE_UNKNOWN;
        return audience;

    }

   private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(securityProperty.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        long expiresIn =  securityProperty.getExpiresIn();
        return new Date(Instant.now().toEpochMilli() + expiresIn  * 1000);
    }

    public int getExpiredIn() {
        return securityProperty.getExpiresIn();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserEntity user = (UserEntity) userDetails;
        final String username = getUsernameFromToken(token);
        return (
                username != null &&
                username.equals(userDetails.getUsername())
        );
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authHeader = getAuthHeaderFromHeader( request );
        if ( authHeader != null && authHeader.startsWith(BEARER)) {
            return authHeader.substring(BEARER.length());
        }

        return null;
    }

    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader(securityProperty.getAuthHeader());
    }

}