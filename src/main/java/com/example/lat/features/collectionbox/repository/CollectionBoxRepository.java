package com.example.lat.features.collectionbox.repository;

import com.example.lat.features.collectionbox.model.CollectionBox;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxRepository extends JpaRepository<CollectionBox, UUID> {}
