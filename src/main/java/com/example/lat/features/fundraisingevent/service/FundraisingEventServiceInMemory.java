package com.example.lat.features.fundraisingevent.service;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.repository.FundraisingEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceInMemory implements FundraisingEventService {
    private final FundraisingEventRepository fundraisingEventRepository;

    @Override
    public FundraisingEvent createFundraisingEvent(
            FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto) {
        FundraisingEvent fundraisingEvent = FundraisingEvent.from(fundraisingEventCreateRequestDto);
        return fundraisingEventRepository.save(fundraisingEvent);
    }

    @Override
    public Page<FundraisingEvent> getFundraisingEvents(Pageable pageable) {
        return fundraisingEventRepository.findAll(pageable);
    }
}
