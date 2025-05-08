package com.example.lat.features.fundraisingevent.service;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.repository.FundraisingEventRepository;
import lombok.RequiredArgsConstructor;
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
}
