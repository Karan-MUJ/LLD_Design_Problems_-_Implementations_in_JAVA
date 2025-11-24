package org.example.strategy;

import org.example.entities.Slot;

import java.util.List;

public interface SlotStrategy {
    public Slot selectSlot(List<Slot> slots);
}
