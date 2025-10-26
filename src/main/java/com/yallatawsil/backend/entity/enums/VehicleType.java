package com.yallatawsil.backend.entity.enums ;


import lombok.Getter;

@Getter
public enum VehicleType {
//  format of enum : enumName(defaultMaxWeight,defaultMaxVolume,defaultMaxDeliveries)
    BIKE(50.0, 0.5, 15),
    VAN(1000.0, 8.0, 50),
    TRUCK(5000.0, 40.0, 100);

    private final Double defaultMaxWeight ;
    private final Double defaultMaxVolume ;
    private final Integer defaultMaxDeliveries ;

    VehicleType(Double defaultMaxWeight, Double defaultMaxVolume, int defaultMaxDeliveries){
        this.defaultMaxWeight = defaultMaxWeight ;
        this.defaultMaxVolume = defaultMaxVolume ;
        this.defaultMaxDeliveries = defaultMaxDeliveries ;
    }
}