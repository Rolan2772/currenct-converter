package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.config.FunctionsConfig;
import com.zooplus.sdc.converter.services.ExchangeRatesService;
import com.zooplus.sdc.converter.services.SignUpService;
import com.zooplus.sdc.converter.services.UserService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FunctionsConfig.class)
@ComponentScan("com.zooplus.sdc.converter.controllers")
public class ControllersContextTestConfig {

    @MockBean
    public ExchangeRatesService exchangeRatesService;
    @MockBean
    public SignUpService signUpService;
    @MockBean
    public UserService userService;
}
