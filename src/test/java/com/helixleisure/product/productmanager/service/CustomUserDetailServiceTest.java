package com.helixleisure.product.productmanager.service;

import com.helixleisure.product.productmanager.entity.UserEntity;
import com.helixleisure.product.productmanager.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testLoadUserByUsernameReturnUser(){
        UserEntity mockUser = new UserEntity();
        mockUser.setId(1L);
        mockUser.setFirstName("Test Name");
        mockUser.setLastName("Test2");
        mockUser.setUsername("testUserName");

        when(userRepository.findByUsername("testUserName")).thenReturn(mockUser);

        UserDetails returnUser = customUserDetailsService.loadUserByUsername("testUserName");
        assertEquals(returnUser, mockUser);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameDoesnotExist(){
        when(userRepository.findByUsername("testUserName")).thenReturn(null);
        UserDetails returnUser = customUserDetailsService.loadUserByUsername("testUserName");

    }

}
