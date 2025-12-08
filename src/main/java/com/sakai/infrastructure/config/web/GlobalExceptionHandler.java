package com.sakai.infrastructure.config.web;

import com.sakai.exception.DomainException;
import com.sakai.logging.DomainLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final DomainLogger log = DomainLogger.get(GlobalExceptionHandler.class);

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomainException(DomainException ex) {
        HttpStatus httpStatus = ApiErrorMapper.mapToHttpStatus(ex.getErrorCode());

        log.warn("Domain error: {}", ex.getMessage());

        return ResponseEntity
            .status(httpStatus)
            .body(Map.of(
                "timestamp", Instant.now(),
                "errorCode", ex.getErrorCode().getCode(),
                "message", ex.getErrorCode().getMessage()
            ));
    }

    /**
     * Validações de @Valid em @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
            .getFieldErrors()
            .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        log.warn("Validation failed for request: {}", errors);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(Map.of(
                "message", "Validation failed",
                "errors", errors
            ));
    }

    /**
     * Fallback para qualquer exceção inesperada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error("Unhandled exception caught: ", ex);

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of(
                "message", "Internal server error",
                "error", ex.getMessage()
            ));
    }
}
