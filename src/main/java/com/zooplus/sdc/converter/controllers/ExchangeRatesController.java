package com.zooplus.sdc.converter.controllers;

import com.zooplus.sdc.converter.entities.ExchangeRateEntity;
import com.zooplus.sdc.converter.functions.ExchangeRateMapper;
import com.zooplus.sdc.converter.services.ExchangeRatesService;
import com.zooplus.sdc.converter.views.ExchangeRateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/rates")
public class ExchangeRatesController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @GetMapping
    public ExchangeRateView getExchangeRate(@Valid ExchangeRateRequest exchangeRateRequest) {
        ExchangeRateEntity entity = exchangeRatesService.getExchangeRate(exchangeRateRequest);
        return exchangeRateMapper.apply(entity);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleValidationFailure(ConstraintViolationException ex) {
        StringBuilder messages = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.append(violation.getMessage())
                    .append("\n");
        }
        return messages.toString();
    }
}