package com.zooplus.sdc.converter.functions;

import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.views.ExchangeRateView;

import java.util.function.Function;

public class ExchangeRateMapper implements Function<ExchangeRateEntity, ExchangeRateView> {

    @Override
    public ExchangeRateView apply(ExchangeRateEntity entity) {
        return new ExchangeRateView(entity.getId(),
                entity.getBaseCurrency().getName(),
                entity.getTargetCurrency().getName(),
                entity.getExchangeRate(),
                entity.getExchangeRateDate(),
                entity.getRequestDate());
    }
}