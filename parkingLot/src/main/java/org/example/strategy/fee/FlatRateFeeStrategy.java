package org.example.strategy.fee;

import org.example.entities.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {
    private static final double RATE_PER_HOUR = 10.0;
    @Override
    public Double calculateFee(ParkingTicket ticket){
        Long duration = ticket.getExitTime() - ticket.getEntryTime();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * RATE_PER_HOUR;
    }
}
