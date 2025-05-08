package com.example.lat.features.collectionbox.service;

import com.example.lat.core.handler.exception.BusinessException;
import com.example.lat.core.handler.exception.BusinessExceptionReason;
import com.example.lat.features.collectionbox.dto.AddMoneyRequestDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxCreateRequestDto;
import com.example.lat.features.collectionbox.model.CollectionBox;
import com.example.lat.features.collectionbox.model.DonationCurrency;
import com.example.lat.features.collectionbox.repository.CollectionBoxRepository;
import com.example.lat.features.collectionbox.repository.DonationCurrencyRepository;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.service.FundraisingEventService;
import com.example.lat.shared.enums.AllowedCurrency;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionBoxServiceInMemory implements CollectionBoxService {
    private final FundraisingEventService fundraisingEventService;
    private final CollectionBoxRepository collectionBoxRepository;
    private final DonationCurrencyRepository donationCurrencyRepository;

    @Transactional
    @Override
    public CollectionBox registerCollectionBox(
            CollectionBoxCreateRequestDto collectionBoxCreateRequestDto) {
        CollectionBox collectionBox = new CollectionBox();
        if (collectionBoxCreateRequestDto.fundraiserEventId().isPresent()) {
            FundraisingEvent fundraisingEvent =
                    fundraisingEventService.getFundraisingEvent(
                            collectionBoxCreateRequestDto.fundraiserEventId().get());
            collectionBox.setFundraisingEvent(fundraisingEvent);
        }
        for (AllowedCurrency currency : collectionBoxCreateRequestDto.currencies()) {
            DonationCurrency donationCurrency =
                    new DonationCurrency(currency.getCurrency(), new BigDecimal("0"));
            DonationCurrency savedDonationCurrency =
                    donationCurrencyRepository.save(donationCurrency);
            collectionBox.addCurrencyDonation(savedDonationCurrency);
        }
        return collectionBoxRepository.save(collectionBox);
    }

    @Override
    public Page<CollectionBox> getCollectionBoxes(Pageable pageable) {
        return collectionBoxRepository.findAll(pageable);
    }

    @Override
    public void deleteCollectionBox(UUID id) {
        Optional<CollectionBox> collectionBox = collectionBoxRepository.findById(id);
        collectionBox.ifPresentOrElse(
                collectionBoxRepository::delete,
                () -> {
                    throw new BusinessException(BusinessExceptionReason.COLLECTION_BOX_NOT_FOUND);
                });
    }

    @Override
    @Transactional
    public CollectionBox assingCollectionBox(UUID collectionBoxId, UUID fundraiserEventId) {
        CollectionBox collectionBox = getCollectionBox(collectionBoxId);
        FundraisingEvent fundraisingEvent =
                fundraisingEventService.getFundraisingEvent(fundraiserEventId);
        collectionBox.setFundraisingEvent(fundraisingEvent);
        return collectionBoxRepository.save(collectionBox);
    }

    @Override
    public void addMoneyToCollectionBox(
            UUID collectionBoxId, AddMoneyRequestDto addMoneyRequestDto) {
        CollectionBox collectionBox = getCollectionBox(collectionBoxId);
        Optional<DonationCurrency> donationCurrency =
                collectionBox.getDonations().stream()
                        .filter(
                                donation ->
                                        donation.getCurrency()
                                                .equals(
                                                        addMoneyRequestDto
                                                                .currency()
                                                                .getCurrency()))
                        .findFirst();
        if (donationCurrency.isPresent()) {
            donationCurrency.get().addMoney(addMoneyRequestDto.amount());
        } else {
            throw new BusinessException(
                    BusinessExceptionReason.DONATION_CURRENCY_NOT_ASSOCIATED_WITH_BOX);
        }
    }

    @Override
    public void transferMoneyFromCollectionBox(UUID collectionBoxId) {
        CollectionBox collectionBox = getCollectionBox(collectionBoxId);
        FundraisingEvent fundraisingEvent = collectionBox.getFundraisingEvent();
        if (fundraisingEvent == null) {
            throw new BusinessException(
                    BusinessExceptionReason.BOX_NOT_CONNECTED_WITH_FUNDRAISER_EVENT);
        }
        // Convert to Fundraiser event currency
    }

    private CollectionBox getCollectionBox(UUID collectionBoxId) {
        return collectionBoxRepository
                .findById(collectionBoxId)
                .orElseThrow(
                        () ->
                                new BusinessException(
                                        BusinessExceptionReason.COLLECTION_BOX_NOT_FOUND));
    }
}
