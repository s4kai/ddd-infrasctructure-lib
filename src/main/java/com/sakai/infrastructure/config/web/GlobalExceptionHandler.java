package com.sakai.infrastructure.config.web;

import com.sakai.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex) {
        HttpStatus httpStatus = ApiErrorMapper.mapToHttpStatus(ex.getErrorCode());
        return ResponseEntity
                .status(httpStatus)
                .body(Map.of(
                        "timestamp", Instant.now(),
                        "errorCode", ex.getErrorCode().getCode(),
                        "message", ex.getErrorCode().getMessage()
                ));
    }
}