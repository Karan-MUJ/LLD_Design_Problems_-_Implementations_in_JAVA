package org.example.state;

import org.example.VendingMachine;
import org.example.entities.Slot;
import org.example.enums.Coin;

public abstract class VendingMachineState {
    VendingMachine machine;
    VendingMachineState(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void insertCoin(Coin coin);
    public abstract void selectProduct(String code);
    public abstract void selectSlot(Slot slot);
    public abstract void dispenseProduct();
    public abstract void refund();
}
