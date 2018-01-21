package com.zooplus.sdc.converter.integration.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeRateResponse {

    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;
}