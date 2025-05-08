package com.example.lat.shared.dto;

import com.example.lat.core.handler.exception.dto.FieldValidationErrorsDto;
import com.example.lat.shared.enums.SuccessCode;
import jakarta.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public record ResponseDto<T>(
        SuccessCode code,
        String message,
        LocalDateTime timestamp,
        @Nullable List<FieldValidationErrorsDto> errors,
        T data) {
    public ResponseDto(
            SuccessCode code, String message, List<FieldValidationErrorsDto> errors, T data) {
        this(code, message, LocalDateTime.now(), errors, data);
    }

    public ResponseDto(SuccessCode code, String message, T data) {
        this(code, message, LocalDateTime.now(), null, data);
    }
}
