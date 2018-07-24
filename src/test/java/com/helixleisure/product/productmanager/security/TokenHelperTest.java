package com.helixleisure.product.productmanager.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenHelperTest{

    @Mock
    private SecurityPropertyContext securityProperty;

    @InjectMocks
    private TokenHelper tokenHelper;

    private final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJwcm9kdWN0LW1hbmFnZXIiLCJzdWIiOiJ1c2VyIiwiYXVkIjoidW5rbm93biIsImlhdCI6MTUzMDAzNzYzMSwiZXhwIjoxOTQwMjU3OTMxfQ.b5NVu-PLTcdbPgFB09h5hngvTuz6idqfowuFFR0xtEpWSJ8ozbXv9mriI2K56_DpX4xmo6cxxdTQirjZeGF2-g";

    @Before
    public void setup(){
        when(securityProperty.getSecret()).thenReturn("queenvictoria");
        when(securityProperty.getAuthHeader()).thenReturn("authorization");
    }

    @Test
    public void getUsernameFromToken(){
        //token is set to expire in 26 june 2031, so this testmight fail then
        String token = TOKEN;
        String username = tokenHelper.getUsernameFromToken(token);
        assertEquals(username, "user");
    }

    @Test
    public void testGetIssuedAtDateFromToken(){
        //token is set to expire in 26 june 2031, so this testmight fail then
        String token = TOKEN;
        Date issuedDate = tokenHelper.getIssuedAtDateFromToken(token);
        assertEquals(issuedDate.getTime()/1000, 1530037631);
    }

    @Test
    public void testGetAuthHeaderFromHeader(){
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("authorization", "Bearer " + TOKEN);
        String returnedToken = tokenHelper.getAuthHeaderFromHeader(mockRequest);
        assertEquals(returnedToken, TokenHelper.BEARER +  TOKEN);
    }

    @Test
    public void testGetToken(){
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("authorization", "Bearer " + TOKEN);
        String returnedToken = tokenHelper.getToken(mockRequest);
        assertEquals(returnedToken, TOKEN);
    }
}
