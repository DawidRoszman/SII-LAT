package com.example.lat.features.fundraisingevent.dto;

import com.example.lat.shared.enums.AllowedCurrency;

public record FundraisingEventCreateRequestDto(String name, AllowedCurrency currency) {}
