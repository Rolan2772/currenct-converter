package com.zooplus.sdc.converter.controllers.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ExchangeRateRequestTest {

    @Test
    public void latestExchangeRate() {
        ExchangeRateRequest request = new ExchangeRateRequest("USD", "GBP");
        assertFalse(request.getExchangeRateDate().isPresent());
    }
}