package com.zooplus.sdc.converter.config;

import com.zooplus.sdc.converter.config.properties.AppProperties;
import com.zooplus.sdc.converter.config.properties.OpenExchangeRateProperties;
import com.zooplus.sdc.converter.integration.ExchangeRateProvider;
import com.zooplus.sdc.converter.integration.OpenExchangeRatesProvider;
import com.zooplus.sdc.converter.services.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(value = {AppProperties.class, OpenExchangeRateProperties.class})
public class AppConfig {

    @Bean
    public ExchangeRatesService exchangeRatesService() {
        return new DefaultExchangeRatesService();
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new OpenExchangeRatesProvider();
    }

    @Bean
    public CurrencyService currencyService() {
        return new DefaultCurrencyService();
    }

    @Bean
    public UserService userService() {
        return new DefaultUserService();
    }

    @Bean
    public SignUpService signUpService() {
        return new DefaultSignUpService();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
