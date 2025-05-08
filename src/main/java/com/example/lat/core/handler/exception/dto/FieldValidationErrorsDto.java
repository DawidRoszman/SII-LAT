package com.example.lat.core.handler.exception.dto;

import com.example.lat.core.handler.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldValidationErrorsDto {
    private ErrorCode code;
    private String field;
    private String message;
}
