package com.example.lat.features.fundraisingevent.repository;

import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, UUID> {}
