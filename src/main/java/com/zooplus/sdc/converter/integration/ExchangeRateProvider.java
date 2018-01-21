package com.zooplus.sdc.converter.integration;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateProvider {

    BigDecimal getLatestExchangeRate(String baseCurrency, String targetCurrency);

    BigDecimal getHistoryExchangeRate(String baseCurrency, String targetCurrency, LocalDate exchangeRateDate);
}
