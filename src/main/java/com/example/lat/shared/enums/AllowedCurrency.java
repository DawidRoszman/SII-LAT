package com.example.lat.shared.enums;

import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AllowedCurrency {
    PLN(Currency.getInstance("PLN")),
    EUR(Currency.getInstance("EUR")),
    USD(Currency.getInstance("USD")),
    GBP(Currency.getInstance("GBP")),
    ;

    private Currency currency;
}
