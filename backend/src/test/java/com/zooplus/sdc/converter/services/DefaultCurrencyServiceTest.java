package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.repositories.CurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;
    @Captor
    private ArgumentCaptor<CurrencyEntity> currencyCaptor;

    @Spy
    @InjectMocks
    private DefaultCurrencyService currencyService;

    @Test
    public void findExistingCurrency() {
        String name = "JPY";
        CurrencyEntity expected = CurrencyEntity.builder()
                .id(1L)
                .name(name)
                .build();
        given(currencyRepository.findByName(name))
                .willReturn(Optional.of(expected));

        CurrencyEntity actual = currencyService.finaByNameOrCreateNew(name);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void createNewCurrency() {
        String aud = "AUD";
        given(currencyRepository.findByName(aud))
                .willReturn(Optional.empty());

        currencyService.finaByNameOrCreateNew(aud);

        verify(currencyRepository).findByName(aud);
        verify(currencyRepository).save(currencyCaptor.capture());
        assertEquals(aud, currencyCaptor.getValue().getName());
    }
}
