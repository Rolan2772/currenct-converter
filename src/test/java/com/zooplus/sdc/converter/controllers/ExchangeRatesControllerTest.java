package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.services.ExchangeRatesService;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExchangeRatesController.class, secure = false)
@ContextConfiguration(classes = ControllersContextTestConfig.class)
public class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Test
    public void latestExchangeRate() throws Exception {
        String baseCurrency = "GBP";
        String targetCurrency = "USD";
        LocalDate exchangeRateDate = LocalDate.now();
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .id(1L)
                .baseCurrency(CurrencyEntity.builder()
                        .id(1L)
                        .name(baseCurrency)
                        .build())
                .targetCurrency(CurrencyEntity.builder()
                        .id(2L)
                        .name(targetCurrency)
                        .build())
                .exchangeRate(BigDecimal.ONE)
                .exchangeRateDate(exchangeRateDate)
                .requestDate(LocalDate.now())
                .build();
        given(exchangeRatesService.getExchangeRate(any()))
                .willReturn(entity);

        mvc.perform(get("/rates")
                .param("baseCurrency", baseCurrency)
                .param("targetCurrency", targetCurrency))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(entity.getId().intValue())))
                .andExpect(jsonPath("$.baseCurrency", is(baseCurrency)))
                .andExpect(jsonPath("$.targetCurrency", is(targetCurrency)))
                .andExpect(jsonPath("$.exchangeRate", Matchers.equalTo(entity.getExchangeRate().intValue())))
                .andExpect(jsonPath("$.exchangeRateDate", is(exchangeRateDate.toString())))
                .andExpect(jsonPath("$.requestDate", is(entity.getRequestDate().toString())));
    }

    @Test
    public void historyExchangeRate() throws Exception {
        String baseCurrency = "JPY";
        String targetCurrency = "EUR";
        LocalDate exchangeRateDate = LocalDate.of(2017, Month.JUNE, 1);
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .id(1L)
                .baseCurrency(CurrencyEntity.builder()
                        .id(1L)
                        .name(baseCurrency)
                        .build())
                .targetCurrency(CurrencyEntity.builder()
                        .id(2L)
                        .name(targetCurrency)
                        .build())
                .exchangeRate(BigDecimal.ONE)
                .exchangeRateDate(exchangeRateDate)
                .requestDate(LocalDate.now())
                .build();
        given(exchangeRatesService.getExchangeRate(any()))
                .willReturn(entity);

        mvc.perform(get("/rates")
                .param("baseCurrency", baseCurrency)
                .param("targetCurrency", targetCurrency)
                .param("exchangeRateDate", exchangeRateDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(entity.getId().intValue())))
                .andExpect(jsonPath("$.baseCurrency", is(baseCurrency)))
                .andExpect(jsonPath("$.targetCurrency", is(targetCurrency)))
                .andExpect(jsonPath("$.exchangeRate", is(entity.getExchangeRate().intValue())))
                .andExpect(jsonPath("$.exchangeRateDate", is(exchangeRateDate.toString())))
                .andExpect(jsonPath("$.requestDate", is(entity.getRequestDate().toString())));
    }

    @Test
    @Ignore
    public void noBaseCurrency() throws Exception {
        mvc.perform(get("/rates")
                .param("targetCurrency", "GBP"))
                .andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value()));
    }
}
