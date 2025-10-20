package org.example.strategy.parkingStrategy;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestFirstStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor: floors){
            Optional <ParkingSpot> spot = floor.findAvailableParkingSpot(vehicle);
            if (spot.isPresent()){
                return spot;
            }
        }
        return Optional.empty();
    }
}
