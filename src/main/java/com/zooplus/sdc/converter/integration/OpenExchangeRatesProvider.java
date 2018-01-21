package com.zooplus.sdc.converter.integration;

import com.zooplus.sdc.converter.config.properties.OpenExchangeRateProperties;
import com.zooplus.sdc.converter.integration.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OpenExchangeRatesProvider implements ExchangeRateProvider {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OpenExchangeRateProperties openExchangeRateProperties;

    public BigDecimal getLatestExchangeRate(String baseCurrency, String targetCurrency) {
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", openExchangeRateProperties.getAppId());
        params.put("base", baseCurrency);
        params.put("symbols", targetCurrency);
        ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(openExchangeRateProperties.getHistoricalUrl(),
                ExchangeRateResponse.class,
                params);
        BigDecimal exchangeRate = response.getBody()
                .getRates()
                .get(targetCurrency);
        return Optional.ofNullable(exchangeRate)
                .orElseThrow(() -> new IllegalStateException(baseCurrency +
                        "/" +
                        targetCurrency +
                        " exchange rate is not found."));
    }

    @Override
    public BigDecimal getHistoryExchangeRate(String baseCurrency, String targetCurrency, LocalDate exchangeRateDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", openExchangeRateProperties.getAppId());
        params.put("base", baseCurrency);
        params.put("symbols", targetCurrency);
        params.put("exchangeRateDate", exchangeRateDate);
        ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(openExchangeRateProperties.getHistoricalUrl(),
                ExchangeRateResponse.class,
                params);
        BigDecimal exchangeRate = response.getBody()
                .getRates()
                .get(targetCurrency);
        return Optional.ofNullable(exchangeRate)
                .orElseThrow(() -> new IllegalStateException(baseCurrency +
                        "/" +
                        targetCurrency +
                        " exchange rate on " +
                        exchangeRateDate +
                        " is not found."));
    }
}
