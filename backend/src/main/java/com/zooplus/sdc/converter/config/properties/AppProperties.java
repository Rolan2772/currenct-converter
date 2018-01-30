package com.zooplus.sdc.converter.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app")
public class AppProperties {

    private int historyItemsMax;
}
