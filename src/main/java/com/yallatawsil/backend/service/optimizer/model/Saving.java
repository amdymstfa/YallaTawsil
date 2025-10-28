package com.yallatawsil.backend.service.optimizer.model ;

import com.yallatawsil.backend.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Saving implements Comparable<Saving>{

    Delivery delivery1 ;
    Delivery delivery2 ;
    private double savingValue ;

    @Override
    public int compareTo(Saving other){
        return Double.compare(other.savingValue, this.savingValue);
    }
}