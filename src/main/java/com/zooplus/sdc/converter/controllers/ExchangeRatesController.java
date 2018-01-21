package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.controllers.model.ExchangeRateRequest;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.functions.ExchangeRateMapper;
import com.zooplus.sdc.converter.services.ExchangeRatesService;
import com.zooplus.sdc.converter.views.ExchangeRateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rates")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @GetMapping
    public ExchangeRateView getExchangeRate(@Valid ExchangeRateRequest exchangeRateRequest) {
        ExchangeRateEntity entity = exchangeRatesService.getExchangeRate(exchangeRateRequest);
        return exchangeRateMapper.apply(entity);
    }
}