package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.CurrencyEntity;

import javax.validation.constraints.NotNull;

public interface CurrencyService {

    CurrencyEntity finaByNameOrCreateNew(@NotNull String name);
}
