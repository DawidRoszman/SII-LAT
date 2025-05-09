package com.example.lat.features.currencyconverter;

import com.example.lat.core.handler.exception.BusinessException;
import com.example.lat.core.handler.exception.BusinessExceptionReason;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class CurrencyConverter {
    private final Map<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {}

    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        if (!rates.containsKey(from.getCurrencyCode())
                || !rates.containsKey(to.getCurrencyCode())) {
            throw new BusinessException(BusinessExceptionReason.RATES_NOT_FOUND);
        }
        BigDecimal fromDecimal = BigDecimal.valueOf(rates.get(from.getCurrencyCode()));
        BigDecimal toDecimal = BigDecimal.valueOf(rates.get(to.getCurrencyCode()));

        BigDecimal amountInUSD = amount.divide(fromDecimal, 4, RoundingMode.HALF_UP);
        BigDecimal result = amountInUSD.multiply(toDecimal);
        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
