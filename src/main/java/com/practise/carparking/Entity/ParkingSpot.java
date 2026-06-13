package com.practise.carparking.Entity;

public class ParkingSpot {
    private final String spotId;
    boolean isFree = true;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
    }
    public String getSpotId() {
        return spotId;
    }
    public boolean isSpotFree() {
        return isFree;
    }
    public void occupySpot() {
        isFree = false;
    }
    public void releaseSpot() {
        isFree = true;
    }


}
