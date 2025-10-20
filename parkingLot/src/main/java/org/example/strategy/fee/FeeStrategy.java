package org.example.strategy.fee;

import org.example.entities.ParkingTicket;

public interface FeeStrategy {
    Double calculateFee(ParkingTicket ticket);
}
