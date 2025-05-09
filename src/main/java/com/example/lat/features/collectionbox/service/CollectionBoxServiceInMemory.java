package com.example.lat.features.collectionbox.service;

import com.example.lat.core.handler.exception.BusinessException;
import com.example.lat.core.handler.exception.BusinessExceptionReason;
import com.example.lat.features.collectionbox.dto.AddMoneyRequestDto;
import com.example.lat.features.collectionbox.dto.CollectionBoxCreateRequestDto;
import com.example.lat.features.collectionbox.model.CollectionBox;
import com.example.lat.features.collectionbox.model.DonationCurrency;
import com.example.lat.features.collectionbox.repository.CollectionBoxRepository;
import com.example.lat.features.collectionbox.repository.DonationCurrencyRepository;
import com.example.lat.features.currencyconverter.CurrencyConverter;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.service.FundraisingEventService;
import com.example.lat.shared.enums.AllowedCurrency;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
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
        collectionBoxCreateRequestDto
                .fundraiserEventId()
                .ifPresent(
                        fundraiserId -> {
                            FundraisingEvent fundraisingEvent =
                                    fundraisingEventService.getFundraisingEvent(fundraiserId);
                            collectionBox.setFundraisingEvent(fundraisingEvent);
                        });
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
        if (collectionBox.getFundraisingEvent() != null) {
            throw new BusinessException(BusinessExceptionReason.COLLECTION_BOX_ALREADY_ASSIGNED);
        }
        FundraisingEvent fundraisingEvent =
                fundraisingEventService.getFundraisingEvent(fundraiserEventId);
        collectionBox.setFundraisingEvent(fundraisingEvent);
        return collectionBoxRepository.save(collectionBox);
    }

    @Override
    public CollectionBox addMoneyToCollectionBox(
            UUID collectionBoxId, AddMoneyRequestDto addMoneyRequestDto) {
        CollectionBox collectionBox = getCollectionBox(collectionBoxId);
        if (collectionBox.getFundraisingEvent() == null) {
            throw new BusinessException(
                    BusinessExceptionReason.BOX_NOT_CONNECTED_WITH_FUNDRAISER_EVENT);
        }
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
            donationCurrencyRepository.save(donationCurrency.get());
        } else {
            throw new BusinessException(
                    BusinessExceptionReason.DONATION_CURRENCY_NOT_ASSOCIATED_WITH_BOX);
        }
        return collectionBoxRepository.save(collectionBox);
    }

    @Transactional
    @Override
    public CollectionBox transferMoneyFromCollectionBox(UUID collectionBoxId) {
        CollectionBox collectionBox = getCollectionBox(collectionBoxId);
        FundraisingEvent fundraisingEvent = collectionBox.getFundraisingEvent();
        if (fundraisingEvent == null) {
            throw new BusinessException(
                    BusinessExceptionReason.BOX_NOT_CONNECTED_WITH_FUNDRAISER_EVENT);
        }
        CurrencyConverter currencyConverter = new CurrencyConverter();
        List<BigDecimal> donationInFundraisingEventCurrency =
                collectionBox.getDonations().stream()
                        .map(
                                donation ->
                                        currencyConverter.convert(
                                                donation.getCurrency(),
                                                fundraisingEvent.getAccountCurrency(),
                                                donation.getAmount()))
                        .toList();

        BigDecimal sumOfDonations =
                donationInFundraisingEventCurrency.stream()
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        fundraisingEvent.addMoney(sumOfDonations);
        collectionBox.getDonations().forEach(donation -> donation.setAmount(BigDecimal.ZERO));
        return collectionBoxRepository.save(collectionBox);
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
