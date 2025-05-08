package com.example.lat.features.collectionbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonationCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    BigDecimal amount;
    Currency currency;
}
