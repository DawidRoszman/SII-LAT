package com.example.lat.features.fundraisingevent.dto;

import com.example.lat.shared.enums.AllowedCurrency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FundraisingEventCreateRequestDto(
        @NotEmpty String name, @NotNull AllowedCurrency currency) {}
