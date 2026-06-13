package com.practise.carparking.Entity;

import com.practise.carparking.parkinglot.ParkingLevel;

import java.time.LocalDateTime;

public class Ticket {
    private final Vehicle vehicle;
    private final ParkingLevel level;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle,
                  ParkingLevel level,
                  ParkingSpot spot) {
        this.vehicle = vehicle;
        this.level = level;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingLevel getLevel() {
        return level;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
