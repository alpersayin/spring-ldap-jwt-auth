package com.alpersayin.ldap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomRequestException.class)
    public ResponseEntity<Object> handleApiException(Exception e) {
        return new ResponseEntity<>(buildApiErrorResponse(e), BAD_REQUEST);
    }

    private CustomErrorResponse buildApiErrorResponse(Exception e) {
        return CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

}
