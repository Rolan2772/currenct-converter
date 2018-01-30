package com.zooplus.sdc.converter.repositories;

import com.zooplus.sdc.converter.entities.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findByName(String name);
}
