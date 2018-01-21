package com.zooplus.sdc.converter.integration;

import com.zooplus.sdc.converter.integration.model.CurrencyPair;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExchangeRateProvider {

    BigDecimal getLatestExchangeRate(CurrencyPair currencyPair);

    BigDecimal getHistoryExchangeRate(CurrencyPair currencyPair, LocalDate exchangeRateDate);
}
