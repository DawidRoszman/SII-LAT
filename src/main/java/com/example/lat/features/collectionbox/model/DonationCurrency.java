package com.example.lat.features.collectionbox.model;

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
public class DonationCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    BigDecimal amount;
    Currency currency;

    public DonationCurrency(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public void addMoney(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }
}
