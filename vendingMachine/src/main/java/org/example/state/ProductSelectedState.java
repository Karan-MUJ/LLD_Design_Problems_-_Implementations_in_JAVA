package org.example.state;

import org.example.VendingMachine;
import org.example.entities.Slot;
import org.example.enums.Coin;

public class ProductSelectedState extends VendingMachineState {
    public ProductSelectedState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.insertCoin(coin);
        if (machine.getSelectedSlot().getProducts().get(0).getPrice() <= coin.getValue()) {
            machine.setMachineState(new HasMoneyState(machine));
            System.out.println("Sufficient coin inserted. You can now dispense the product.");
        } else refund();
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("Product already selected. Please insert coin or refund.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please insert coin to dispense the product.");
    }

    @Override
    public void selectSlot(Slot slot) {
        System.out.println("Slot already selected. Please insert coin or refund.");
    }

    @Override
    public void refund() {
        machine.reset();
        machine.setMachineState(new IdleState(machine));
    }
}
