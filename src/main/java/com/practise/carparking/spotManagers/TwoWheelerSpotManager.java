package com.practise.carparking.spotManagers;

import com.practise.carparking.Entity.ParkingSpot;
import com.practise.carparking.LookupStrategy.ParkingSpotLookUpStrategy;

import java.util.List;

public class TwoWheelerSpotManager extends ParkingSpotManager{
    public TwoWheelerSpotManager(List<ParkingSpot>spots, ParkingSpotLookUpStrategy strategy){
        super(spots,strategy);
    }
}
