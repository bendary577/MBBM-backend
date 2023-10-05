package com.mbbm.app.authentication.service;

import com.mbbm.app.security.userDetails.UserDetailsServiceImpl;
import com.mbbm.app.service.LoginService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @Mock
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private LoginService loginService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void canAuthenticateUser(){}


}
