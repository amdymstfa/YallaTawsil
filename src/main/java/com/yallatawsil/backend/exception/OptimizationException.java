package com.yallatawsil.backend.exception;

public class OptimizationException extends RuntimeException {

    public OptimizationException(String message) {
        super(message);
    }

    public OptimizationException(String message, Throwable cause) {
        super(message, cause);
    }
}