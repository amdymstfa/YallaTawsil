package com.yallatawsil.backend.entity.enums ;

public enum TourStatus {
    PLANNED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    /**
     * Set a current status depending on the stat
     */
    public boolean canTransitionTo(TourStatus newStatus){
        return switch (this){
            case PLANNED -> newStatus == IN_PROGRESS || newStatus == CANCELLED ;
            case IN_PROGRESS -> newStatus == COMPLETED || newStatus == CANCELLED ;
            case COMPLETED, CANCELLED -> false ;
        };
    }
}