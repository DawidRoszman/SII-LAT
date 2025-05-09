package com.example.lat.features.collectionbox.dto;

import com.example.lat.shared.enums.AllowedCurrency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public record CollectionBoxCreateRequestDto(
        Optional<UUID> fundraiserEventId,
        @NotNull @Size(min = 1, message = "Collection box must have at least one currency")
                Set<AllowedCurrency> currencies) {}
