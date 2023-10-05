package com.mbbm.app.authentication.controller;

import com.mbbm.app.controller.authentication.AuthenticationController;
import com.mbbm.app.service.LoginService;
import com.mbbm.app.service.SignupService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {

    private static final String AUTH_API_BASIC_URL = "/api/v1/auth/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private SignupService signupService;


    @Test
    public void givenUserExists_whenLogin_thenReturnJWTToken() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get(AUTH_API_BASIC_URL+"login");
        MvcResult mvcResult = this.mockMvc.perform(request).andReturn();
        assertEquals("", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginShouldReturnOkMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

}
