package com.practise.carparking.spotManagers;

import com.practise.carparking.Entity.ParkingSpot;
import com.practise.carparking.LookupStrategy.ParkingSpotLookUpStrategy;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class ParkingSpotManager {
    protected final List<ParkingSpot> spots;
    protected final ParkingSpotLookUpStrategy strategy;
    private final ReentrantLock lock = new ReentrantLock(true);

    protected ParkingSpotManager(List<ParkingSpot> spots,
                                 ParkingSpotLookUpStrategy strategy) {
        this.spots = spots;
        this.strategy = strategy;
    }

    public ParkingSpot park(){
        lock.lock();
        try{
            ParkingSpot spot = strategy.selectSpot(spots);
            if(spot ==null){
                return null;
            }
            spot.occupySpot();
            return spot;
        }finally {
            lock.unlock();
        }
    }
    public void unPark(ParkingSpot spot){
        lock.lock();
        try{
            spot.releaseSpot();
        }finally{
            lock.unlock();
        }
    }

    public boolean hasFreeSpot(){
        lock.lock();
        try{
            return spots.stream().anyMatch(ParkingSpot::isSpotFree);
        }finally{
            lock.unlock();
        }

    }
}
