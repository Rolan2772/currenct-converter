package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import com.zooplus.sdc.converter.services.SignUpService;
import com.zooplus.sdc.converter.views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signup")
    public UserView signUp(@RequestBody SignUpRequest signUpRequest) {
        Authentication auth = signUpService.signUp(signUpRequest);
        return new UserView(auth.getName());
    }

}
