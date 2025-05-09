package com.example.lat.features.collectionbox.dto;

import com.example.lat.shared.enums.AllowedCurrency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AddMoneyRequestDto(
        @NotNull AllowedCurrency currency,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal amount) {}
