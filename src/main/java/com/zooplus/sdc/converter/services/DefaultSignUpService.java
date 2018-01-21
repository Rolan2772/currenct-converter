package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotNull;

@Slf4j
public class DefaultSignUpService implements SignUpService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Authentication signUp(@NotNull SignUpRequest signUpRequest) {
        log.info("Registering new user: {}", signUpRequest.getUserName());
        UserEntity userEntity = userService.createUser(signUpRequest.getUserName());
        Authentication token = new UsernamePasswordAuthenticationToken(userEntity.getName(), "");
        authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(token);
        log.info("User '{}' is automatically logged in.", token.getPrincipal());
        return token;
    }
}
