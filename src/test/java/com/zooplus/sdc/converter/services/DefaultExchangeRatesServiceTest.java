package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.ExchangeRateRequest;
import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.integration.ExchangeRateProvider;
import com.zooplus.sdc.converter.repositories.ExchangeRateRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DefaultExchangeRatesServiceTest {

    private static final String BASE_CURRENCY = "USD";
    private static final String TARGET_CURRENCY = "DKK";

    @Mock
    private ExchangeRateProvider exchangeRateProvider;
    @Mock
    private CurrencyService currencyService;
    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Spy
    @InjectMocks
    private DefaultExchangeRatesService exchangeRatesService;

    @Before
    public void before() {
        given(exchangeRateProvider.getLatestExchangeRate(any(), any()))
                .willReturn(BigDecimal.ONE);
        given(exchangeRateProvider.getHistoryExchangeRate(any(), any(), any()))
                .willReturn(BigDecimal.TEN);
        given(currencyService.finaByNameOrCreateNew(BASE_CURRENCY))
                .willReturn(CurrencyEntity.builder()
                        .id(1L)
                        .name(BASE_CURRENCY)
                        .build());
        given(currencyService.finaByNameOrCreateNew(TARGET_CURRENCY))
                .willReturn(CurrencyEntity.builder()
                        .id(2L)
                        .name(TARGET_CURRENCY)
                        .build());
        willAnswer(invocationOnMock -> {
            ExchangeRateEntity entity = (ExchangeRateEntity) invocationOnMock.getArguments()[0];
            entity.setId(1L);
            return entity;
        })
                .given(exchangeRateRepository)
                .save(Matchers.<ExchangeRateEntity>any());
    }

    @Test
    public void getLatestExchangeRate() {
        ExchangeRateRequest request = new ExchangeRateRequest(BASE_CURRENCY, TARGET_CURRENCY);

        ExchangeRateEntity entity = exchangeRatesService.getExchangeRate(request);

        assertNotNull(entity);
        assertEquals(Long.valueOf(1), entity.getId());
        assertEquals(BASE_CURRENCY, entity.getBaseCurrency().getName());
        assertEquals(TARGET_CURRENCY, entity.getTargetCurrency().getName());
        assertEquals(BigDecimal.ONE, entity.getExchangeRate());
        assertEquals(LocalDate.now(), entity.getExchangeRateDate());
        assertEquals(LocalDate.now(), entity.getRequestDate());
    }

    @Test
    public void getHistoryExchangeRate() {
        LocalDate exchangeRateDate = LocalDate.now().minusMonths(1);
        ExchangeRateRequest request = new ExchangeRateRequest(BASE_CURRENCY, TARGET_CURRENCY, exchangeRateDate);

        ExchangeRateEntity entity = exchangeRatesService.getExchangeRate(request);

        assertNotNull(entity);
        assertEquals(Long.valueOf(1), entity.getId());
        assertEquals(BASE_CURRENCY, entity.getBaseCurrency().getName());
        assertEquals(TARGET_CURRENCY, entity.getTargetCurrency().getName());
        assertEquals(BigDecimal.TEN, entity.getExchangeRate());
        assertEquals(exchangeRateDate, entity.getExchangeRateDate());
        assertEquals(LocalDate.now(), entity.getRequestDate());
    }
}
