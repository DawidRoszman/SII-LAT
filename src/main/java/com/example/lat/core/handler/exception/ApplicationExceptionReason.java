package com.example.lat.core.handler.exception;

import com.example.lat.core.handler.exception.policy.ApplicationExceptionPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionReason implements ApplicationExceptionPolicy {
    BEAN_PROPERTY_NOT_EXISTS("Property '%s' for object '%s' doesn't exists"),
    NO_WRITERS_FOUND("No writers found for WebP");

    private final String code = name();
    private final String message;
}
