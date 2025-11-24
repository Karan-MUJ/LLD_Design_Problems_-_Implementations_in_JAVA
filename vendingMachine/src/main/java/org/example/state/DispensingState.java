package org.example.state;

import org.example.VendingMachine;
import org.example.entities.Inventory;

public class DispensingState extends VendingMachineState {
    public DispensingState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(org.example.enums.Coin coin) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void selectSlot(org.example.entities.Slot slot) {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Dispensing in progress. Please wait.");
    }

    @Override
    public void refund() {
        System.out.println("Please wait, returning change in progress.");
        machine.returnChange();
    }
}
