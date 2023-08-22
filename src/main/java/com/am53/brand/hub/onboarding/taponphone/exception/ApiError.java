package com.am53.brand.hub.onboarding.taponphone.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Data
public class ApiError extends Exception {

    @NonNull
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    @NonNull
    private String errorMessage;

    private String path;
    private String error;
    private String message;
    private List<String> details;

    public ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus httpStatus, String message, String path) {
        this();
        this.status = httpStatus;
        this.error = httpStatus.name();
        this.path = path;
        this.message = message;
        this.details = new ArrayList<>();
    }

    public ApiError(HttpStatus httpStatus, String message, String path, List<String> details) {
        this(httpStatus, message, path);
        this.details = details;
    }

}
