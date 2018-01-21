package com.zooplus.sdc.converter.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "exchange_request")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = {"id",
        "baseCurrency",
        "targetCurrency",
        "exchangeRate"})
public class ExchangeRateEntity {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "base_currency_id")
    private CurrencyEntity baseCurrency;
    @OneToOne
    @JoinColumn(name = "target_currency_id")
    private CurrencyEntity targetCurrency;
    @Column(nullable = false)
    private BigDecimal exchangeRate;
    @Column(nullable = false)
    private LocalDate exchangeRateDate;
    @Column(nullable = false)
    private LocalDate requestDate;
}
