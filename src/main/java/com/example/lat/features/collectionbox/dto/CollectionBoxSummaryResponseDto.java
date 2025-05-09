package com.example.lat.features.collectionbox.dto;

import com.example.lat.features.collectionbox.model.CollectionBox;
import java.math.BigDecimal;
import java.util.UUID;

public record CollectionBoxSummaryResponseDto(UUID id, Boolean isAssigned, Boolean isEmpty) {
    public static CollectionBoxSummaryResponseDto from(CollectionBox collectionBox) {
        return new CollectionBoxSummaryResponseDto(
                collectionBox.getId(),
                collectionBox.getFundraisingEvent() != null,
                collectionBox.getDonations().stream()
                        .allMatch(
                                donation -> donation.getAmount().compareTo(BigDecimal.ZERO) == 0));
    }
}
