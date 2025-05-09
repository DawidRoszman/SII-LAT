package com.example.lat.features.fundraisingevent.dto;

import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record FundraisingEventResponseDto(
        UUID id, String name, BigDecimal accountAmount, Currency accountCurrency)
        implements Serializable {
    public static FundraisingEventResponseDto from(FundraisingEvent fundraisingEvent) {
        if (fundraisingEvent == null) {
            return null;
        }
        return new FundraisingEventResponseDto(
                fundraisingEvent.getId(),
                fundraisingEvent.getName(),
                fundraisingEvent.getAccountAmount(),
                fundraisingEvent.getAccountCurrency());
    }
}
