package com.zooplus.sdc.converter.security;

import com.zooplus.sdc.converter.entities.UserEntity;
import com.zooplus.sdc.converter.services.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserNameAuthenticationProviderTest {

    public static final String USER_NAME = "John";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserService userService;

    @Spy
    @InjectMocks
    private UserNameAuthenticationProvider provider;

    @Before
    public void before() {
        given(userService.findByName(USER_NAME))
                .willReturn(Optional.of(UserEntity.builder()
                        .id(1L)
                        .name(USER_NAME)
                        .build()));
    }

    @Test
    public void authenticate() {
        Authentication token = new UsernamePasswordAuthenticationToken(USER_NAME, "");

        Authentication auth = provider.authenticate(token);

        assertEquals(USER_NAME, auth.getName());
        assertTrue(auth.isAuthenticated());
    }

    @Test
    public void wrongUser() {
        given(userService.findByName(anyString()))
                .willReturn(Optional.empty());
        Authentication auth = new UsernamePasswordAuthenticationToken("wrongUser", "");

        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage("Cannot find user 'wrongUser'");
        provider.authenticate(auth);
    }
}
