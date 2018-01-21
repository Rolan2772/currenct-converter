package com.zooplus.sdc.converter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("integration.openExchangeRatesProperties")
public class OpenExchangeRateProperties {

    private String appId;
    private String apiUrl;
    private String latestUrl;
    private String historicalUrl;
}
