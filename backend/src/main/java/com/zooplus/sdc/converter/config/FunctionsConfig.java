package com.zooplus.sdc.converter.config;

import com.zooplus.sdc.converter.functions.ExchangeRateMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FunctionsConfig {

    @Bean
    public ExchangeRateMapper exchangeRateMapper() {
        return new ExchangeRateMapper();
    }
}
