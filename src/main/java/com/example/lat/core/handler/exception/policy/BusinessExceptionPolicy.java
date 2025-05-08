package com.example.lat.core.handler.exception.policy;

import com.example.lat.core.handler.exception.dto.FieldValidationErrorsDto;
import java.util.List;
import org.springframework.http.HttpStatus;

public interface BusinessExceptionPolicy extends ExceptionPolicy {
    HttpStatus getHttpStatus();

    List<FieldValidationErrorsDto> getErrors();
}
