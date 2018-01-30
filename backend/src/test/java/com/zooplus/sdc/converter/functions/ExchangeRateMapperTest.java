package com.zooplus.sdc.converter.functions;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.views.ExchangeRateView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateMapperTest {

    @Spy
    private ExchangeRateMapper mapper;

    @Test
    public void map() {
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .id(1L)
                .baseCurrency(CurrencyEntity.builder()
                        .id(1L)
                        .name("SEK")
                        .build())
                .targetCurrency(CurrencyEntity.builder()
                        .id(2L)
                        .name("USD")
                        .build())
                .exchangeRate(BigDecimal.ONE)
                .exchangeRateDate(LocalDate.now())
                .requestDate(LocalDate.now())
                .build();

        ExchangeRateView view = mapper.apply(entity);

        assertEquals(view.getId(), entity.getId());
        assertEquals(view.getBaseCurrency(), entity.getBaseCurrency().getName());
        assertEquals(view.getTargetCurrency(), entity.getTargetCurrency().getName());
        assertEquals(view.getExchangeRate(), entity.getExchangeRate());
        assertEquals(view.getExchangeRateDate(), entity.getExchangeRateDate());
        assertEquals(view.getRequestDate(), entity.getRequestDate());
    }
}
