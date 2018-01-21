package com.zooplus.sdc.converter.integration.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class CurrencyPair {

    private String baseCurrency;
    private String targetCurrency;

    @Override
    public String toString() {
        return baseCurrency + "/" + targetCurrency;
    }
}
