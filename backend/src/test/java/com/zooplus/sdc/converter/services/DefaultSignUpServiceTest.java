package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.entities.UserEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DefaultSignUpServiceTest {

    public static final String USER_NAME = "Nick";

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;

    @Spy
    @InjectMocks
    private DefaultSignUpService defaultSignUpService;

    @Before
    public void before() {
        given(userService.createUser(USER_NAME))
                .willReturn(UserEntity.builder()
                        .id(1L)
                        .name(USER_NAME)
                        .build());
    }

    @Test
    public void signUp() {
        SignUpRequest request = new SignUpRequest(USER_NAME);

        Authentication auth = defaultSignUpService.signUp(request);

        assertEquals(USER_NAME, auth.getPrincipal().toString());
        assertEquals(auth, SecurityContextHolder.getContext().getAuthentication());
    }


}
