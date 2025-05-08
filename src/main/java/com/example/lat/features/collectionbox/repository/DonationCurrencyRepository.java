package com.example.lat.features.collectionbox.repository;

import com.example.lat.features.collectionbox.model.DonationCurrency;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationCurrencyRepository extends JpaRepository<DonationCurrency, UUID> {}
