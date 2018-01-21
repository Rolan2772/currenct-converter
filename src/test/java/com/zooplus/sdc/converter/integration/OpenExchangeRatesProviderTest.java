package com.zooplus.sdc.converter.integration;

import com.zooplus.sdc.converter.config.properties.OpenExchangeRateProperties;
import com.zooplus.sdc.converter.integration.model.ExchangeRateResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OpenExchangeRatesProviderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private OpenExchangeRateProperties openExchangeRateProperties;
    @Mock
    private ResponseEntity<ExchangeRateResponse> exchangeRateResponseEntity;

    @Spy
    @InjectMocks
    private OpenExchangeRatesProvider openExchangeRatesProvider;

    @Before
    public void before() {
        given(restTemplate.<ExchangeRateResponse>getForEntity(anyString(), any(), Matchers.anyMapOf(String.class, Object.class)))
                .willReturn(exchangeRateResponseEntity);
    }

    @Test
    public void getLatestExchangeRate() {
        String baseCurrency = "USD";
        String targetCurrency = "JPY";
        BigDecimal expectedRate = BigDecimal.valueOf(10.4325);
        given(exchangeRateResponseEntity.getBody())
                .willReturn(createResponse(baseCurrency, targetCurrency, expectedRate));

        BigDecimal exchangeRate = openExchangeRatesProvider.getLatestExchangeRate(baseCurrency, targetCurrency);

        assertEquals(expectedRate, exchangeRate);
    }

    @Test
    public void latestRateNotFound() {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setRates(new HashMap<>());
        given(exchangeRateResponseEntity.getBody())
                .willReturn(response);

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("USD/GBP exchange rate is not found.");
        openExchangeRatesProvider.getLatestExchangeRate("USD", "GBP");
    }

    @Test
    public void getHistoryExchangeRate() {
        String baseCurrency = "ADU";
        String targetCurrency = "USD";
        LocalDate historyDate = LocalDate.of(2015, Month.JANUARY, 23);
        BigDecimal expectedRate = BigDecimal.valueOf(10.4325);
        given(exchangeRateResponseEntity.getBody())
                .willReturn(createResponse(baseCurrency, targetCurrency, expectedRate));

        BigDecimal exchangeRate = openExchangeRatesProvider.getHistoryExchangeRate(baseCurrency, targetCurrency, historyDate);

        assertEquals(expectedRate, exchangeRate);
    }

    @Test
    public void historyRateNotFound() {
        LocalDate historyDate = LocalDate.of(2015, Month.JANUARY, 23);
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setRates(new HashMap<>());
        given(exchangeRateResponseEntity.getBody())
                .willReturn(response);

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("USD/GBP exchange rate on " + historyDate.toString() + " is not found.");
        openExchangeRatesProvider.getHistoryExchangeRate("USD", "GBP", historyDate);
    }

    private ExchangeRateResponse createResponse(String baseCurrency, String targetCurrency, BigDecimal expectedRate) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setBase(baseCurrency);
        response.setTimestamp(System.currentTimeMillis());
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(targetCurrency, expectedRate);
        response.setRates(rates);
        return response;
    }
}
