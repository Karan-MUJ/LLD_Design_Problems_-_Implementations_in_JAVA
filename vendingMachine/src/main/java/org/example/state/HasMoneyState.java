package org.example.state;

import org.example.VendingMachine;
import org.example.entities.Slot;
import org.example.enums.Coin;

public class HasMoneyState extends VendingMachineState {
    public HasMoneyState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Coin already inserted. You can dispense the product.");
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("Product already selected. You can dispense the product or refund.");
    }

    @Override
    public void dispenseProduct() {
        if (machine.getSelectedSlot() != null) {
            System.out.println("Dispensing product from slot " + machine.getSelectedSlot().getId());
            machine.dispenseProduct();
        }
        machine.setMachineState(new DispensingState(machine));
        machine.reset();
    }

    @Override
    public void selectSlot(Slot slot) {
        System.out.println("Slot already selected. You can dispense the product or refund.");
    }

    @Override
    public void refund() {
        machine.setMachineState(new IdleState(machine));
        machine.reset();
    }
}
