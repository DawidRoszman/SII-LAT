package com.example.lat.core.handler.exception;

import com.example.lat.core.handler.exception.dto.FieldValidationErrorsDto;
import com.example.lat.core.handler.exception.policy.BusinessExceptionPolicy;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason implements BusinessExceptionPolicy {
    TEST("", HttpStatus.BAD_REQUEST, null);

    private final String code = name();
    private final String message;
    private final HttpStatus httpStatus;
    private final List<FieldValidationErrorsDto> errors;
}
