package com.yallatawsil.backend.entity.enums ;

public enum DeliveryStatus {
    PENDING,
    INT_TRANSIT,
    DELIVERED,
    FAILED ;

    /**
     * Decide when transition is a new status
     * @param newStatus
     * @return the current status depend the state
     */
    public boolean canTransitionTo(DeliveryStatus newStatus){
        return switch (this){
            case PENDING -> newStatus == INT_TRANSIT ;
            case INT_TRANSIT -> newStatus == DELIVERED || newStatus == FAILED ;
            case DELIVERED , FAILED ->  false ;
        };
    }
}