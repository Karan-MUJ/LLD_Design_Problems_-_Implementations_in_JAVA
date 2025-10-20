package org.example.strategy.parkingStrategy;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
