package com.zooplus.sdc.converter.views;

import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class ExchangeRateView {

    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal exchangeRate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate exchangeRateDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate requestDate;
}
