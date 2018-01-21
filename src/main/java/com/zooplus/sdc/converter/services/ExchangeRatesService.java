package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.controllers.ExchangeRateRequest;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;

public interface ExchangeRatesService {

    ExchangeRateEntity getExchangeRate(ExchangeRateRequest exchangeRateRequest);
}
