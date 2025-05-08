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
    COLLECTION_BOX_NOT_FOUND("Could not find collection box", HttpStatus.NOT_FOUND, null),
    FUNDRAISER_EVENT_NOT_FOUND("Could not find fundraiser event", HttpStatus.NOT_FOUND, null),
    DONATION_CURRENCY_NOT_ASSOCIATED_WITH_BOX(
            "Could not find donation currency associated with the box", HttpStatus.NOT_FOUND, null),
    BOX_NOT_CONNECTED_WITH_FUNDRAISER_EVENT(
            "Box is not connected with fundraiser event", HttpStatus.BAD_REQUEST, null),
    RATES_NOT_FOUND("Could not find rates", HttpStatus.NOT_FOUND, null),
    ;

    private final String code = name();
    private final String message;
    private final HttpStatus httpStatus;
    private final List<FieldValidationErrorsDto> errors;
}
