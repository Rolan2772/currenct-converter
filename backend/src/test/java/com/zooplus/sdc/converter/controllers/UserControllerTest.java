package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.entities.UserEntity;
import com.zooplus.sdc.converter.services.SignUpService;
import com.zooplus.sdc.converter.services.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class, secure = false)
@ContextConfiguration(classes = ControllersContextTestConfig.class)
public class UserControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private MockMvc mvc;
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserService userService;

    @Before
    public void before() {
        given(userService.findByName(anyString()))
                .willReturn(Optional.empty());
    }

    @Test
    public void signUp() throws Exception {
        SignUpRequest request = new SignUpRequest("Morty");
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getUserName(), "", new ArrayList<>());
        given(signUpService.signUp(request))
                .willReturn(auth);

        mvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"" + request.getUserName() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(request.getUserName())));
    }

    @Test
    public void signUpRegisteredUser() throws Exception {
        SignUpRequest request = new SignUpRequest("Morty");
        given(userService.findByName(anyString()))
                .willReturn(Optional.of(UserEntity.builder()
                        .id(1L)
                        .name(request.getUserName())
                        .build()));

        expectedException.expect(NestedServletException.class);
        expectedException.expectMessage("User already exists.");
        mvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"" + request.getUserName() + "\"}"));

    }
}
