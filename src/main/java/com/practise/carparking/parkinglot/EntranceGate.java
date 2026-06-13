package com.practise.carparking.parkinglot;

import com.practise.carparking.Entity.Ticket;
import com.practise.carparking.Entity.Vehicle;

public class EntranceGate {

    public Ticket enter(ParkingBuilding building, Vehicle vehicle) {
        return building.allocate(vehicle);
    }

}
