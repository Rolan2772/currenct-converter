package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.model.SignUpRequest;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotNull;

public interface SignUpService {

    Authentication signUp(@NotNull SignUpRequest signUpRequest);
}
