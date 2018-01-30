package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.model.ExchangeRateRequest;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.integration.ExchangeRateProvider;
import com.zooplus.sdc.converter.integration.model.CurrencyPair;
import com.zooplus.sdc.converter.repositories.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Transactional
public class DefaultExchangeRatesService implements ExchangeRatesService {

    @Autowired
    private ExchangeRateProvider exchangeRateProvider;
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateEntity getExchangeRate(ExchangeRateRequest request) {
        log.info("Loading exchange rate {}", request);
        CurrencyPair currencyPair = CurrencyPair.of(request.getBaseCurrency(), request.getTargetCurrency());
        LocalDate exchangeRateDate = request.getExchangeRateDate()
                .orElse(LocalDate.now());
        BigDecimal exchangeRate = loadExchangeRate(currencyPair, exchangeRateDate);
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .baseCurrency(currencyService.finaByNameOrCreateNew(currencyPair.getBaseCurrency()))
                .targetCurrency(currencyService.finaByNameOrCreateNew(currencyPair.getTargetCurrency()))
                .exchangeRate(exchangeRate)
                .exchangeRateDate(exchangeRateDate)
                .requestDate(LocalDate.now())
                .build();
        return exchangeRateRepository.save(entity);
    }

    private BigDecimal loadExchangeRate(CurrencyPair currencyPair, LocalDate exchangeRateDate) {
        return exchangeRateDate.isBefore(LocalDate.now())
                ? exchangeRateProvider.getHistoryExchangeRate(currencyPair, exchangeRateDate)
                : exchangeRateProvider.getLatestExchangeRate(currencyPair);
    }
}
