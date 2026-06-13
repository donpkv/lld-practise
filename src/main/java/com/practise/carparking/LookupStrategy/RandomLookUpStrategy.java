package com.practise.carparking.LookupStrategy;

import com.practise.carparking.Entity.ParkingSpot;

import java.util.List;

public class RandomLookUpStrategy implements ParkingSpotLookUpStrategy {
    @Override
    public ParkingSpot selectSpot(List<ParkingSpot> spots){
        for(ParkingSpot ps:spots){
            if(ps.isSpotFree()){
                return ps;
            }
        }
        return null;
    }
}
