package com.practise.carparking.Entity;

import com.practise.carparking.enums.VehicleType;

public class Vehicle {
    String vehicleNumber;
    VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

}
