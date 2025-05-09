package com.example.lat.features.collectionbox.dto;

import com.example.lat.features.collectionbox.model.DonationCurrency;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record DonationCurrencyResponseDto(UUID id, BigDecimal amount, Currency currency)
        implements Serializable {
    public static DonationCurrencyResponseDto from(DonationCurrency donationCurrency) {
        if (donationCurrency == null) {
            return null;
        }
        return new DonationCurrencyResponseDto(
                donationCurrency.getId(),
                donationCurrency.getAmount(),
                donationCurrency.getCurrency());
    }
}
