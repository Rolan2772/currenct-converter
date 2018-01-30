package com.zooplus.sdc.converter.integration;

import com.zooplus.sdc.converter.config.properties.OpenExchangeRateProperties;
import com.zooplus.sdc.converter.exceptions.OpenExchangeIntegrationException;
import com.zooplus.sdc.converter.integration.model.CurrencyPair;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OpenExchangeRatesProviderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<ExchangeRateResponse> exchangeRateResponseEntity;
    @Mock
    private OpenExchangeRateProperties openExchangeRateProperties;

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
        CurrencyPair currencyPair = CurrencyPair.of("USD", "JPY");
        BigDecimal expectedRate = BigDecimal.valueOf(10.4325);
        given(exchangeRateResponseEntity.getBody())
                .willReturn(createResponse(currencyPair, expectedRate));

        BigDecimal exchangeRate = openExchangeRatesProvider.getLatestExchangeRate(currencyPair);

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
        openExchangeRatesProvider.getLatestExchangeRate(CurrencyPair.of("USD", "GBP"));
    }

    @Test
    public void exchangeRateProviderIsNotAvailable() {
        willThrow(new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Open exchange rates service is not available."))
                .given(restTemplate)
                .getForEntity(anyString(), any(), Matchers.anyMapOf(String.class, Object.class));

        expectedException.expect(OpenExchangeIntegrationException.class);
        expectedException.expectMessage("Failed to load latest USD/GBP exchange rate.");
        openExchangeRatesProvider.getLatestExchangeRate(CurrencyPair.of("USD", "GBP"));
    }

    @Test
    public void getHistoryExchangeRate() {
        CurrencyPair currencyPair = CurrencyPair.of("AUD", "USD");
        LocalDate historyDate = LocalDate.of(2015, Month.JANUARY, 23);
        BigDecimal expectedRate = BigDecimal.valueOf(10.4325);
        given(exchangeRateResponseEntity.getBody())
                .willReturn(createResponse(currencyPair, expectedRate));

        BigDecimal exchangeRate = openExchangeRatesProvider.getHistoryExchangeRate(currencyPair, historyDate);

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
        openExchangeRatesProvider.getHistoryExchangeRate(CurrencyPair.of("USD", "GBP"), historyDate);
    }

    private ExchangeRateResponse createResponse(CurrencyPair currencyPair, BigDecimal expectedRate) {
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setBase(currencyPair.getBaseCurrency());
        response.setTimestamp(System.currentTimeMillis());
        Map<String, BigDecimal> rates = new HashMap<>();
        rates.put(currencyPair.getTargetCurrency(), expectedRate);
        response.setRates(rates);
        return response;
    }
}
