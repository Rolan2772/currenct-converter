package com.zooplus.sdc.converter.repositories;

import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {
}
