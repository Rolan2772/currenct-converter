package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.config.FunctionsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FunctionsConfig.class)
@ComponentScan("com.zooplus.sdc.converter.controllers")
public class ControllersContextTestConfig {
}
