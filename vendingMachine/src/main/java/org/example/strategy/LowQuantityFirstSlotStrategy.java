package org.example.strategy;

import org.example.entities.Slot;

import java.util.List;

public class LowQuantityFirstSlotStrategy implements SlotStrategy {
    @Override
    public Slot selectSlot(List<Slot> slots) {
        Slot leastFilledSlot = slots.get(0);
        for (Slot slot: slots) {
            if (slot.getProducts().size() < leastFilledSlot.getProducts().size()) {
                leastFilledSlot = slot;
            }
        }
        return leastFilledSlot;
    }
}
