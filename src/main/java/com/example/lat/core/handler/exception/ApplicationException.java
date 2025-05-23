package com.example.lat.core.handler.exception;

import static java.lang.String.format;

import com.example.lat.core.handler.exception.policy.ApplicationExceptionPolicy;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException implements ApplicationExceptionPolicy {
    @Serial private static final long serialVersionUID = 1L;
    private final String code;
    private final String message;

    @SuppressWarnings("unused")
    public ApplicationException(final ApplicationExceptionReason reason) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
    }

    @SuppressWarnings("unused")
    public ApplicationException(
            final ApplicationExceptionReason reason, final Object... parameters) {
        if (parameters != null) {
            this.message = format(reason.getMessage(), parameters);
        } else {
            this.message = reason.getMessage();
        }

        this.code = reason.getCode();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    @Override
    public String toString() {
        return format(
                "ApplicationException(code=%s, message=%s)", this.getCode(), this.getMessage());
    }
}
