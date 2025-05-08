package com.example.lat.features.fundraisingevent.service;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;

public interface FundraisingEventService {
    FundraisingEvent createFundraisingEvent(
            FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto);
}
