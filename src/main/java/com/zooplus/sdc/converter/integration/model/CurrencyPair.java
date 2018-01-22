package com.zooplus.sdc.converter.integration.model;

import lombok.Value;

import javax.validation.constraints.NotNull;

@Value(staticConstructor = "of")
public class CurrencyPair {

    private String baseCurrency;
    private String targetCurrency;

    private CurrencyPair(@NotNull String baseCurrency, @NotNull String targetCurrency) {
        this.baseCurrency = baseCurrency.toUpperCase();
        this.targetCurrency = targetCurrency.toUpperCase();
    }

    public static CurrencyPair of(@NotNull String baseCurrency, @NotNull String targetCurrency) {
        return new CurrencyPair(baseCurrency, targetCurrency);
    }

    @Override
    public String toString() {
        return baseCurrency + "/" + targetCurrency;
    }
}
