package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Transactional
public class DefaultCurrencyService implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CurrencyEntity finaByNameOrCreateNew(@NotNull String name) {
        return currencyRepository.findByName(name)
                .orElseGet(() -> currencyRepository.save(CurrencyEntity.builder()
                        .name(name)
                        .build()));
    }
}
