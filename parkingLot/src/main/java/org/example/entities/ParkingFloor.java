package org.example.entities;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final Map<String, ParkingSpot> parkingSpots;
    private final Long floor;

    public ParkingFloor(Long floor) {
        this.floor = floor;
        parkingSpots = new HashMap<>();
    }

    public void addSpot(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getSpotId(), parkingSpot);
    }

    public synchronized Optional<ParkingSpot> findAvailableParkingSpot(Vehicle vehicle) {
        return parkingSpots.values().stream().filter(spot -> spot.isAvailable() && spot.canFitVehicle(vehicle)).sorted(Comparator.comparing(ParkingSpot::getSpotSize)).findFirst();
    }

    public void displayAvailability() {
        System.out.println("<----- Floor " + floor + " Availability ----->");
        Map<VehicleSize, Long> availableCounts = parkingSpots.values().stream().filter(spot -> !spot.isAvailable()).collect(Collectors.groupingBy(ParkingSpot::getSpotSize, Collectors.counting()));
        for (VehicleSize vehicleSize : VehicleSize.values()) {
            System.out.println(availableCounts.getOrDefault(vehicleSize, 0L) + vehicleSize.toString() + " slots available");
        }
    }
}
