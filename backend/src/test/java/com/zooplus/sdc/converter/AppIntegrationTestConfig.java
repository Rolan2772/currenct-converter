package com.zooplus.sdc.converter;

import com.zooplus.sdc.converter.config.AppConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
@ComponentScan("com.zooplus.sdc.converter")
@EnableAutoConfiguration
public class AppIntegrationTestConfig {
}
