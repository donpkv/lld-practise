package com.practise.carrental.product;

import com.practise.carrental.reservation.Reservation;
import com.practise.carrental.reservation.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class VehicleInventoryManager {

    private final Map<Integer,Vehicle> vehicles= new ConcurrentHashMap<>();
    private final Map<Integer, List<Integer>> vehicleBookingIds = new ConcurrentHashMap<>();

    private final Map<Integer, ReentrantLock> vehicleLocks = new ConcurrentHashMap<>();

    private ReservationRepository reservationRepository;
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleID(), vehicle);
    }

    public Optional<Vehicle> getVehicle(int vehicleId) {
        return Optional.ofNullable(vehicles.get(vehicleId));
    }

    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    private ReentrantLock lockForVehicle(int vehicleId) {
        vehicleLocks.putIfAbsent(vehicleId, new ReentrantLock());
        return vehicleLocks.get(vehicleId);
    }



    public boolean isAvailable(int vehicleId, LocalDate from, LocalDate to){
        Vehicle vehicle = vehicles.get(vehicleId);
        if(vehicle==null)return false;
        if (vehicle.getVehicleStatus() == VehicleStatus.MAINTENANCE) return false;

        DateInterval requested = new DateInterval(from,to);

        List<Integer> reservationIDs = vehicleBookingIds.get(vehicleId);

        if(reservationIDs == null || reservationIDs.isEmpty()) {
            return true;
        }

        for(int reservationID :reservationIDs){
            Reservation reservation = reservationRepository.findById(reservationID).get();
            LocalDate bookedFrom = reservation.getDateBookedFrom();
            LocalDate bookedTill = reservation.getDateBookedTo();
            DateInterval bookedInterval = new DateInterval(bookedFrom, bookedTill);
            if (bookedInterval.overlaps(requested)) {
                return false;
            }
        }
        return true;
    }


    public boolean reserve(int vehicleId, int reservationId, LocalDate from, LocalDate to){
        ReentrantLock lock = lockForVehicle(vehicleId);

        lock.lock();

        try{
            if(!isAvailable(vehicleId,from,to)){
                return false;
            }
            vehicleBookingIds.putIfAbsent(vehicleId, new ArrayList<>());
            vehicleBookingIds.get(vehicleId).add(reservationId);

            vehicles.get(vehicleId).setStatus(VehicleStatus.BOOKED);

            return true;

        }finally {
            lock.unlock();
        }
    }

    public void release(int vehicleId, int reservationId) {
        ReentrantLock lock = lockForVehicle(vehicleId);
        lock.lock();
        try{
            List<Integer> ids = vehicleBookingIds.get(vehicleId);
            if (ids != null) {
                ids.remove(Integer.valueOf(reservationId));
            }
            List<Integer> stillBooked = vehicleBookingIds.get(vehicleId);
            if (stillBooked == null || stillBooked.isEmpty()) {
                vehicles.get(vehicleId).setStatus(VehicleStatus.AVAILABLE);
            }

        } finally {
            lock.unlock();
        }
    }
    public List<Vehicle> getAvailableVehicles(
            VehicleType type,
            LocalDate from,
            LocalDate to
    ) {
        return vehicles.values()
                .stream()
                .filter(v -> v.getVehicleType() == type)
                .filter(v -> isAvailable(v.getVehicleID(), from, to))
                .collect(Collectors.toList());
    }



}
