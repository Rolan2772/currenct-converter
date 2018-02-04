package com.zooplus.sdc.converter.security;

import com.zooplus.sdc.converter.entities.UserEntity;
import com.zooplus.sdc.converter.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationTest {

    public static final String USER_NAME = "Jane";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Before
    public void before() {
        given(userService.findByName(USER_NAME))
                .willReturn(Optional.of(UserEntity.builder()
                        .id(1L)
                        .name(USER_NAME)
                        .build()));
    }

    @Test
    public void login() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .userParameter("userName")
                .user(USER_NAME)
                .password("");

        mockMvc.perform(login)
                .andExpect(authenticated().withUsername(USER_NAME));
    }

    @Test
    public void invalidUser() throws Exception {
        given(userService.findByName(anyString()))
                .willReturn(Optional.empty());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
                .user("wrong")
                .password("");

        mockMvc.perform(login)
                .andExpect(unauthenticated());
    }
}