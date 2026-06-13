package com.practise.carparking.LookupStrategy;

import com.practise.carparking.Entity.ParkingSpot;
import java.util.List;

public interface ParkingSpotLookUpStrategy {
    ParkingSpot selectSpot(List<ParkingSpot> spots);
}
