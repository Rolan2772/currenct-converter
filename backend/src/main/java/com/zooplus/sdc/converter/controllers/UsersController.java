package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.services.SignUpService;
import com.zooplus.sdc.converter.services.UserService;
import com.zooplus.sdc.converter.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public UserView getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new UserView(auth.getPrincipal().toString());
    }

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
        boolean anonymous = auth instanceof AnonymousAuthenticationToken;
        boolean authenticated = auth != null && auth.isAuthenticated();
        if (!anonymous && authenticated) {
            throw new IllegalArgumentException("Please logout first before sign up.");
        }
    }

}
