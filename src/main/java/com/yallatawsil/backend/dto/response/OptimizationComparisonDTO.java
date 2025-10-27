package com.yallatawsil.backend.dto.response ;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OptimizationComparisonDTO {

    private TourResponseDTO nearestNeighborResult ;
    private TourResponseDTO clarkeWrightResult ;
    private Long nnExecutionTimesMs ;
    private Long cwExecutionTimesMs ;
    private  Double distanceImprovement ;
    private Double improvementPercentage ;

    public void calculateImprovement(){
        if (nearestNeighborResult != null && clarkeWrightResult != null){
            Double nnDistance = this.nearestNeighborResult.getTotalDistance();
            Double cwDistance = this.clarkeWrightResult.getTotalDistance() ;

            if (nnDistance != null && cwDistance != null && nnDistance > 0){
                this.distanceImprovement = nnDistance - cwDistance ;
                this.improvementPercentage = (distanceImprovement / nnDistance) * 100 ;
            }
        }
    }
}