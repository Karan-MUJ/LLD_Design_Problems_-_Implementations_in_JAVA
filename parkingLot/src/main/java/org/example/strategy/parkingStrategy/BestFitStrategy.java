package org.example.strategy.parkingStrategy;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle){
        Optional <ParkingSpot> bestSpot = Optional.empty();
        for(ParkingFloor floor: floors){
            Optional <ParkingSpot> spotOnThisFloor = floor.findAvailableParkingSpot(vehicle);
            if (bestSpot.isPresent()){
                if (spotOnThisFloor.get().getSpotSize().ordinal() < bestSpot.get().getSpotSize().ordinal()){
                    bestSpot = spotOnThisFloor;
                }
            }else{
                bestSpot = spotOnThisFloor;
            }
        }
        return bestSpot;
    }
}
