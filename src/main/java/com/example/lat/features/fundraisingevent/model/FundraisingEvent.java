package com.example.lat.features.fundraisingevent.model;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FundraisingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    // The variables with prefix "account" could be separated to another entity but the task stated
    // to keep it simple
    private BigDecimal accountAmount;
    private Currency accountCurrency;

    public FundraisingEvent(String name, Currency accountCurrency, BigDecimal accountAmount) {
        this.name = name;
        this.accountCurrency = accountCurrency;
        this.accountAmount = accountAmount;
    }

    public static FundraisingEvent from(
            FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto) {
        Currency currency = Currency.getInstance(fundraisingEventCreateRequestDto.currencyCode());
        return new FundraisingEvent(
                fundraisingEventCreateRequestDto.name(),
                currency,
                // I assumed that when creating fundraising event the amount is 0
                new BigDecimal("0"));
    }
}
