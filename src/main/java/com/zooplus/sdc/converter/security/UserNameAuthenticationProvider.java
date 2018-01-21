package com.zooplus.sdc.converter.security;

import com.zooplus.sdc.converter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserNameAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userName = authentication.getName();
        return userService.findByName(userName)
                .map(user -> new UsernamePasswordAuthenticationToken(user.getName(), "", new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user '" + userName + "'"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
