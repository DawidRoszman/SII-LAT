package com.example.lat.features.fundraisingevent.controller;

import com.example.lat.features.fundraisingevent.dto.FundraisingEventCreateRequestDto;
import com.example.lat.features.fundraisingevent.dto.FundraisingEventResponseDto;
import com.example.lat.features.fundraisingevent.model.FundraisingEvent;
import com.example.lat.features.fundraisingevent.service.FundraisingEventService;
import com.example.lat.shared.dto.ResponseDto;
import com.example.lat.shared.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fundraising-event")
@RequiredArgsConstructor
public class FundraisingEventController {
    private final FundraisingEventService fundraisingEventService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("create")
    public ResponseDto<FundraisingEventResponseDto> createFundraisingEvent(
            @RequestBody @Valid FundraisingEventCreateRequestDto fundraisingEventCreateRequestDto) {
        FundraisingEvent fundraisingEvent =
                fundraisingEventService.createFundraisingEvent(fundraisingEventCreateRequestDto);
        FundraisingEventResponseDto fundraisingEventResponseDto =
                FundraisingEventResponseDto.from(fundraisingEvent);
        return new ResponseDto<>(
                SuccessCode.RESOURCE_CREATED,
                "Successfully created fundraising event",
                fundraisingEventResponseDto);
    }

    @GetMapping
    public ResponseDto<Page<FundraisingEventResponseDto>> getFundraisingEvents(
            @ParameterObject
                    @PageableDefault(
                            sort = "id",
                            direction = Sort.Direction.DESC,
                            size = 10,
                            page = 0)
                    Pageable pageable) {
        Page<FundraisingEvent> fundraisingEvents =
                fundraisingEventService.getFundraisingEvents(pageable);
        return new ResponseDto<>(
                SuccessCode.RESPONSE_SUCCESSFUL,
                "Successfully fetched fundraising events",
                fundraisingEvents.map(FundraisingEventResponseDto::from));
    }
}
