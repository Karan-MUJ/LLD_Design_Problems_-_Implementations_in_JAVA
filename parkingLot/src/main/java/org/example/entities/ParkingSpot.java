package org.example.entities;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

public class
ParkingSpot {
    private final String spotId;
    private Boolean isOccupied;
    private Vehicle parkedVehicle;
    private final VehicleSize spotSize;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public Boolean isOccupied() {
        return this.isOccupied;
    }

    public Boolean isAvailable(){
        return !this.isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    public String getSpotId() {
        return this.spotId;
    }

    public VehicleSize getSpotSize() {
        return this.spotSize;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public Boolean canFitVehicle(Vehicle vehicle) {
        if (this.isOccupied) return false;
        switch (vehicle.getSize()) {
            case SMALL:
                return this.spotSize == VehicleSize.SMALL;
            case MEDIUM:
                return this.spotSize == VehicleSize.MEDIUM;
            case LARGE:
                return this.spotSize == VehicleSize.LARGE;
            default: return false;
        }
    }
}
