package com.example.lat.features.fundraisingevent.controller;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.dto.FundraisingEventResponseDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.service.FundraisingEventService;
import com.example.lat.shared.dto.ResponseDto;
import com.example.lat.shared.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fundraising-event")
@RequiredArgsConstructor
public class FundraisingEventController {
    private final FundraisingEventService fundraisingEventService;

    @PostMapping("create")
    public ResponseDto<FundraisingEventResponseDto> createFundraisingEvent(
            @RequestBody FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto) {
        FundraisingEvent fundraisingEvent =
                fundraisingEventService.createFundraisingEvent(fundraisingEventCreateRequestDto);
        FundraisingEventResponseDto fundraisingEventResponseDto =
                FundraisingEventResponseDto.from(fundraisingEvent);
        return new ResponseDto<>(
                SuccessCode.RESOURCE_CREATED,
                "Successfully created fundraising event",
                fundraisingEventResponseDto);
    }
}
