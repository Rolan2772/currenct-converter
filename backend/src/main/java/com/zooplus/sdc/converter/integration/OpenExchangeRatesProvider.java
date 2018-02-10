package com.zooplus.sdc.converter.integration;

import com.zooplus.sdc.converter.config.properties.OpenExchangeRateProperties;
import com.zooplus.sdc.converter.exceptions.OpenExchangeIntegrationException;
import com.zooplus.sdc.converter.integration.model.CurrencyPair;
import com.zooplus.sdc.converter.integration.model.ExchangeRateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
public class OpenExchangeRatesProvider implements ExchangeRateProvider {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OpenExchangeRateProperties openExchangeRateProperties;

    public BigDecimal getLatestExchangeRate(CurrencyPair currencyPair) {
        log.info("Requesting latest {} exchange rate.", currencyPair);
        try {
            Map<String, Object> params = createRequestParams(currencyPair);
            ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(openExchangeRateProperties.getLatestApi(), ExchangeRateResponse.class, params);
            String emptyRateMessage = currencyPair + " exchange rate is not found.";
            return getExchangeRate(response.getBody(), currencyPair.getTargetCurrency(), emptyRateMessage);
        } catch (HttpStatusCodeException exception) {
            String message = "Failed to load latest " + currencyPair + " exchange rate.";
            throw new OpenExchangeIntegrationException(message, exception);
        }
    }

    private Map<String, Object> createRequestParams(CurrencyPair currencyPair) {
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", openExchangeRateProperties.getAppId());
        params.put("base", currencyPair.getBaseCurrency());
        params.put("symbols", currencyPair.getTargetCurrency());
        return params;
    }

    private BigDecimal getExchangeRate(ExchangeRateResponse response, String targetCurrency, String emptyRateMessage) {
        BigDecimal exchangeRate = response
                .getRates()
                .get(targetCurrency);
        return Optional.ofNullable(exchangeRate)
                .orElseThrow(createError(emptyRateMessage));
    }

    private Supplier<IllegalStateException> createError(String message) {
        return () -> new IllegalStateException(message);
    }

    @Override
    public BigDecimal getHistoryExchangeRate(CurrencyPair currencyPair, LocalDate exchangeRateDate) {
        log.info("Requesting {} exchange rate on {}.", currencyPair, exchangeRateDate);
        try {
            Map<String, Object> params = createRequestParams(currencyPair);
            params.put("exchangeRateDate", exchangeRateDate);
            ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(openExchangeRateProperties.getHistoricalApi(), ExchangeRateResponse.class, params);
            String emptyRateMessage = currencyPair + " exchange rate on " + exchangeRateDate + " is not found.";
            return getExchangeRate(response.getBody(), currencyPair.getTargetCurrency(), emptyRateMessage);
        } catch (HttpStatusCodeException exception) {
            String message = "Failed to load " + currencyPair + " historical exchange rate on " + exchangeRateDate + ".";
            throw new OpenExchangeIntegrationException(message, exception);
        }
    }
}
