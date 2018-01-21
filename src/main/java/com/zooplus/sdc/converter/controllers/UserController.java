package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.services.SignUpService;
import com.zooplus.sdc.converter.services.UserService;
import com.zooplus.sdc.converter.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserView signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        String userName = signUpRequest.getUserName();
        validateSession();
        validateUser(userName);
        Authentication auth = signUpService.signUp(signUpRequest);
        return new UserView(auth.getName());
    }

    private void validateUser(String userName) {
        userService.findByName(userName)
                .ifPresent(userEntity -> {
                    throw new IllegalArgumentException("User already exists.");
                });
    }

    private void validateSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            throw new IllegalArgumentException("Please logout first before sign up.");
        }
    }

}
