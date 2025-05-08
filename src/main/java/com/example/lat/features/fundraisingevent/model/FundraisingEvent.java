package com.example.lat.features.fundraisingevent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

@Entity
@Getter
@Setter
public class FundraisingEvent {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private BigDecimal accountAmount;
    private Currency accountCurrency;
}
