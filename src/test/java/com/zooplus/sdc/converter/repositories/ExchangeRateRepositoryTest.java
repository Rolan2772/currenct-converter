package com.zooplus.sdc.converter.repositories;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ExchangeRateRepositoryTest {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findOne() {
        CurrencyEntity aud = entityManager.persist(CurrencyEntity.builder()
                .name("AUD")
                .build());
        CurrencyEntity nzd = entityManager.persist(CurrencyEntity.builder()
                .name("NZD")
                .build());
        ExchangeRateEntity entity = ExchangeRateEntity.builder()
                .baseCurrency(aud)
                .targetCurrency(nzd)
                .exchangeRate(BigDecimal.valueOf(1.09787702))
                .exchangeRateDate(LocalDate.now().minusWeeks(1))
                .requestDate(LocalDate.now())
                .build();
        Long id = entityManager.persist(entity).getId();

        assertNotNull(exchangeRateRepository.findOne(id));
    }
}
