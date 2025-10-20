package org.example.strategy.parkingStrategy;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FarthestFirstStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        //Finding spot in the topMost floor possible
        List <ParkingFloor> reversedFloors = new ArrayList<>(parkingFloors);
        Collections.reverse(reversedFloors);
        for (ParkingFloor floor: parkingFloors) {
            Optional<ParkingSpot> spot = floor.findAvailableParkingSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
