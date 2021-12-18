package com.alpersayin.ldap.exception;

public class CustomRequestException extends RuntimeException {
    public CustomRequestException(String message, Exception e) { super(message); }
}
