package com.example.expenseTracker.exceptionHandling.exception;

import lombok.Data;

public class UnAuthorizedAccessException extends RuntimeException{
    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}
