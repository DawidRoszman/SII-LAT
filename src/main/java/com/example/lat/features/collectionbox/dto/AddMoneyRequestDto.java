package com.example.lat.features.collectionbox.dto;

import com.example.lat.shared.enums.AllowedCurrency;
import java.math.BigDecimal;

public record AddMoneyRequestDto(AllowedCurrency currency, BigDecimal amount) {}
