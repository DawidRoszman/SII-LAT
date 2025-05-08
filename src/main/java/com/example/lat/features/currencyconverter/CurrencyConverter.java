package com.example.lat.features.currencyconverter;

import com.example.lat.core.handler.exception.BusinessException;
import com.example.lat.core.handler.exception.BusinessExceptionReason;
import com.example.lat.shared.enums.AllowedCurrency;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private final Map<Currency, BigDecimal> rates = new HashMap<>();

    public CurrencyConverter() {
        rates.put(AllowedCurrency.GBP.getCurrency(), BigDecimal.valueOf(1.4));
        rates.put(AllowedCurrency.USD.getCurrency(), BigDecimal.valueOf(1.0));
        rates.put(AllowedCurrency.PLN.getCurrency(), BigDecimal.valueOf(0.7));
        rates.put(AllowedCurrency.EUR.getCurrency(), BigDecimal.valueOf(1.1));
    }

    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        if (!rates.containsKey(from) || !rates.containsKey(to)) {
            throw new BusinessException(BusinessExceptionReason.RATES_NOT_FOUND);
        }

        BigDecimal amountInUSD = amount.divide(rates.get(from), 4, RoundingMode.HALF_UP);
        return amountInUSD.multiply(rates.get(to));
    }
}
