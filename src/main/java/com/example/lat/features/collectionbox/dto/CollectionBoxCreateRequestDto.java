package com.example.lat.features.collectionbox.dto;

import com.example.lat.shared.enums.AllowedCurrency;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record CollectionBoxCreateRequestDto(
        Optional<UUID> fundraiserEventId, List<AllowedCurrency> currencies) {}
