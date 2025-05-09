package com.example.lat.features.collectionbox.dto;

import com.example.lat.features.collectionbox.model.CollectionBox;
import com.example.lat.features.fundraisingevent.dto.FundraisingEventResponseDto;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record CollectionBoxResponseDto(
        UUID id,
        FundraisingEventResponseDto fundraisingEvent,
        List<DonationCurrencyResponseDto> donations)
        implements Serializable {
    public static CollectionBoxResponseDto from(CollectionBox collectionBox) {
        return new CollectionBoxResponseDto(
                collectionBox.getId(),
                FundraisingEventResponseDto.from(collectionBox.getFundraisingEvent()),
                collectionBox.getDonations().stream()
                        .map(DonationCurrencyResponseDto::from)
                        .toList());
    }
}
