package com.example.lat.features.fundraisingevent.service;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundraisingEventService {
    FundraisingEvent createFundraisingEvent(
            FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto);

    Page<FundraisingEvent> getFundraisingEvents(Pageable pageable);

    FundraisingEvent getFundraisingEvent(UUID id);
}
