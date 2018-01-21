package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.ExchangeRateRequest;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.integration.ExchangeRateProvider;
import com.zooplus.sdc.converter.repositories.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Transactional
public class DefaultExchangeRatesService implements ExchangeRatesService {

    @Autowired
    private ExchangeRateProvider exchangeRateProvider;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateEntity getExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        String baseCurrency = exchangeRateRequest.getBaseCurrency();
        String targetCurrency = exchangeRateRequest.getTargetCurrency();
        LocalDate now = LocalDate.now();
        LocalDate exchangeRateDate = exchangeRateRequest.getExchangeRateDate()
                .orElse(now);
        BigDecimal exchangeRate = exchangeRateDate.isBefore(now)
                ? exchangeRateProvider.getHistoryExchangeRate(baseCurrency, targetCurrency, exchangeRateDate)
                : exchangeRateProvider.getLatestExchangeRate(baseCurrency, targetCurrency);
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .baseCurrency(currencyService.finaByNameOrCreateNew(baseCurrency))
                .targetCurrency(currencyService.finaByNameOrCreateNew(targetCurrency))
                .exchangeRate(exchangeRate)
                .exchangeRateDate(exchangeRateDate)
                .requestDate(LocalDate.now())
                .build();
        return exchangeRateRepository.save(entity);
    }
}
