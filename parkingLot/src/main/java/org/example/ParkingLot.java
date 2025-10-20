package org.example;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.entities.ParkingTicket;
import org.example.strategy.fee.FeeStrategy;
import org.example.strategy.parkingStrategy.ParkingStrategy;
import org.example.vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;
    private final List<ParkingFloor> floorList;
    private final Map <String, ParkingTicket> activeParkingTickets;

    private ParkingLot(ParkingStrategy parkingStrategy, FeeStrategy feeStrategy){
        floorList = new ArrayList<>();
        activeParkingTickets = new ConcurrentHashMap<>();
        this.parkingStrategy = parkingStrategy;
        this.feeStrategy = feeStrategy;
    }
    public static ParkingLot getInstance(ParkingStrategy parkingStrategy, FeeStrategy feeStrategy){
        if (parkingLot==null){
            parkingLot = new ParkingLot(parkingStrategy, feeStrategy);
        }
        return parkingLot;
    }
    public void setParkingStrategy(ParkingStrategy parkingStrategy){ this.parkingStrategy = parkingStrategy; }
    public void setFeeStrategy(FeeStrategy feeStrategy){ this.feeStrategy = feeStrategy; }
    public void addFloor(Long floorId, ParkingFloor parkingFloor){
        floorList.add(parkingFloor);
    }
    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle){
        Optional<ParkingSpot> spot = parkingStrategy.findSpot(floorList, vehicle);
        if (spot.isPresent()){
            ParkingSpot availableSpot = spot.get();
            availableSpot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, availableSpot);
            activeParkingTickets.put(vehicle.getLicenseNumber(), ticket);
            System.out.println(vehicle.getLicenseNumber() + " parked at " + availableSpot.getSpotId() + ". Ticket: " + ticket.getTicketId());
            return Optional.of(ticket);
        }
        System.out.println("No spot available for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }
    public Optional<Double> unparkVehicle(String licenseNumber){
        ParkingTicket ticket = activeParkingTickets.remove(licenseNumber);
        if (ticket == null){
            System.out.println("No parking ticket found for " + licenseNumber);
        }
        ticket.setExitTime();
        ticket.getParkingSpot().unparkVehicle();
        Double parkingFee = feeStrategy.calculateFee(ticket);
        return Optional.of(parkingFee);
    }
}
