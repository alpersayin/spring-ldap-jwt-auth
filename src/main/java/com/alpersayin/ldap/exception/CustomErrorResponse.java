package com.alpersayin.ldap.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomErrorResponse {
    private LocalDateTime timestamp;
    int status;
    private String error;
    private String message;
}
