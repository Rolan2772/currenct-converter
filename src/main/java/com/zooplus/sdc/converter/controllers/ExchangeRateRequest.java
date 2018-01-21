package com.zooplus.sdc.converter.controllers;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Optional;

import static com.zooplus.sdc.converter.controllers.ValidationConstants.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequest {

    @NotNull(message = BASE_CURRENCY_IS_REQUIRED)
    @Pattern(regexp = CURRENCY_PATTERN, message = WRONG_CURRENCY_FORMAT)
    private String baseCurrency;
    @NotNull(message = TARGET_CURRENCY_IS_REQUIRED)
    @Pattern(regexp = CURRENCY_PATTERN, message = WRONG_CURRENCY_FORMAT)
    private String targetCurrency;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Getter(AccessLevel.NONE)
    private LocalDate exchangeRateDate;

    public ExchangeRateRequest(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Optional<LocalDate> getExchangeRateDate() {
        return Optional.ofNullable(exchangeRateDate);
    }
}
