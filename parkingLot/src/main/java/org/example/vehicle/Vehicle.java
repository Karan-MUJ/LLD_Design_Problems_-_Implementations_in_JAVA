package org.example.vehicle;

public abstract class Vehicle {
    private final String licenseNumber;
    private final VehicleSize size;
    public Vehicle(String licenseNumber, VehicleSize size) {
        this.licenseNumber = licenseNumber;
        this.size = size;
    }
    public VehicleSize getSize(){
        return size;
    }
    public String getLicenseNumber(){
        return licenseNumber;
    }
}
