package com.sakai.infrastructure.config.web;

import com.sakai.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ApiErrorMapper {
    public static HttpStatus mapToHttpStatus(ErrorCode errorCode) {
        return switch (errorCode) {
            case BUSINESS_RULE_VIOLATION -> HttpStatus.CONFLICT;
            case ACCESS_DENIED -> HttpStatus.FORBIDDEN;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.BAD_REQUEST;
        };
    }

}
