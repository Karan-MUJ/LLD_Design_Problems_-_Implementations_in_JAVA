package org.example.entities;

import org.example.vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final Long entryTime;
    private Long exitTime;
    private final ParkingSpot parkingSpot;
    private final Vehicle vehicle;
    private final String ticketId;

    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot){
        this.vehicle = vehicle;
        this.entryTime = new Date().getTime();
        this.parkingSpot = parkingSpot;
        this.ticketId = UUID.randomUUID().toString();
    }

    public Long getEntryTime() { return entryTime; }
    public Long getExitTime() { return exitTime; }
    public String getTicketId() { return ticketId; }
    public ParkingSpot getParkingSpot() { return parkingSpot; }
    public Vehicle getVehicle() { return vehicle; }
    public void setExitTime(){ this.exitTime = new Date().getTime(); }
}
