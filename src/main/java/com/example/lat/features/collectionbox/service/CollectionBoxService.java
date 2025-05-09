package com.example.lat.features.collectionbox.service;

import com.example.lat.features.collectionbox.dto.AddMoneyRequestDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxCreateRequestDto;
import com.example.lat.features.collectionbox.model.CollectionBox;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CollectionBoxService {
    CollectionBox registerCollectionBox(
            CollectionBoxCreateRequestDto collectionBoxCreateRequestDto);

    Page<CollectionBox> getCollectionBoxes(Pageable pageable);

    void deleteCollectionBox(UUID id);

    CollectionBox assingCollectionBox(UUID collectionBoxId, UUID fundraiserEventId);

    CollectionBox addMoneyToCollectionBox(
            UUID collectionBoxId, AddMoneyRequestDto addMoneyRequestDto);

    CollectionBox transferMoneyFromCollectionBox(UUID collectionBoxId);
}
