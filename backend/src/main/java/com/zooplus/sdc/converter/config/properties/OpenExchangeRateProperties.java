package com.zooplus.sdc.converter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("integration.oxr")
public class OpenExchangeRateProperties {

    private String appId;
    private String latestApi;
    private String historicalApi;
}
