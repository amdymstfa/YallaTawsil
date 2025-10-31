package com.yallatawsil.backend.exception;

public class VehicleCapacityExceededException extends RuntimeException {

    public VehicleCapacityExceededException(String message) {
        super(message);
    }
}