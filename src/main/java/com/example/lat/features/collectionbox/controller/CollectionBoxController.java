package com.example.lat.features.collectionbox.controller;

import com.example.lat.features.collectionbox.dto.AddMoneyRequestDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxCreateRequestDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxResponseDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxSummaryResponseDto;
import com.example.lat.features.collectionbox.model.CollectionBox;
import com.example.lat.features.collectionbox.service.CollectionBoxService;
import com.example.lat.shared.dto.ResponseDto;
import com.example.lat.shared.enums.SuccessCode;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collection-box")
@RequiredArgsConstructor
public class CollectionBoxController {
    private final CollectionBoxService collectionBoxService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    public ResponseDto<CollectionBoxResponseDto> register(
            @RequestBody @Valid CollectionBoxCreateRequestDto collectionBoxCreateRequestDto) {
        CollectionBox collectionBox =
                collectionBoxService.registerCollectionBox(collectionBoxCreateRequestDto);
        return new ResponseDto<>(
                SuccessCode.RESOURCE_CREATED,
                "Successfully registered collection box",
                CollectionBoxResponseDto.from(collectionBox));
    }

    @GetMapping
    public ResponseDto<Page<CollectionBoxSummaryResponseDto>> getAll(
            @ParameterObject @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<CollectionBox> collectionBoxes = collectionBoxService.getCollectionBoxes(pageable);
        return new ResponseDto<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched collection boxes",
                collectionBoxes.map(CollectionBoxSummaryResponseDto::from));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}/unregister")
    public void unregister(@PathVariable UUID id) {
        collectionBoxService.deleteCollectionBox(id);
    }

    @PatchMapping("{collectionBoxId}/assign/{fundraisingEventId}")
    public ResponseDto<CollectionBoxResponseDto> assignFundraisingEvent(
            @PathVariable UUID collectionBoxId, @PathVariable UUID fundraisingEventId) {
        CollectionBox collectionBox =
                collectionBoxService.assignCollectionBox(collectionBoxId, fundraisingEventId);
        return new ResponseDto<>(
                SuccessCode.RESOURCE_UPDATED,
                "Successfully assigned fundraising event to collection box",
                CollectionBoxResponseDto.from(collectionBox));
    }

    @PatchMapping("{id}/donate")
    public ResponseDto<CollectionBoxResponseDto> donate(
            @PathVariable UUID id, @RequestBody @Valid AddMoneyRequestDto addMoneyRequestDto) {
        CollectionBox collectionBox =
                collectionBoxService.addMoneyToCollectionBox(id, addMoneyRequestDto);

        return new ResponseDto<>(
                SuccessCode.RESOURCE_UPDATED,
                "Successfully dontated money to the collection box",
                CollectionBoxResponseDto.from(collectionBox));
    }

    @PatchMapping("{id}/transfer")
    public ResponseDto<CollectionBoxResponseDto> transfer(@PathVariable UUID id) {
        CollectionBox collectionBox = collectionBoxService.transferMoneyFromCollectionBox(id);

        return new ResponseDto<>(
                SuccessCode.RESOURCE_UPDATED,
                "Successfully transferred donations to fundraising event",
                CollectionBoxResponseDto.from(collectionBox));
    }
}
