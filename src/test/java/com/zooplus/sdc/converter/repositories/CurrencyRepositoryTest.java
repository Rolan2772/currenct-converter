package com.zooplus.sdc.converter.repositories;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName() {
        String name = "AUD";
        CurrencyEntity aud = entityManager.persist(CurrencyEntity.builder()
                .name(name)
                .build());
        entityManager.persist(aud);

        Optional<CurrencyEntity> persisted = currencyRepository.findByName(name);
        assertTrue(persisted.isPresent());
        assertEquals(name, persisted.get().getName());
    }

    @Test
    public void notFoundByName() {
        assertFalse(currencyRepository.findByName("EUR").isPresent());
    }
}
