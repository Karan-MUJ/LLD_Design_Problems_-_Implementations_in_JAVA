package org.example.state;

import org.example.VendingMachine;
import org.example.entities.Slot;
import org.example.enums.Coin;

public class IdleState extends VendingMachineState {
    public IdleState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product before inserting coins.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first.");
    }

    @Override
    public void refund() {
        System.out.println("No item to dispense.");
    }

    @Override
    public void selectProduct(String code) {
        if (machine.getInventory().isProductAvailable(code) == false) {
            System.out.println("Product " + code + " is out of stock. Please select another product.");
            return;
        }
        System.out.println("Product " + code + " selected. You can now insert coins.");
        machine.setSelectedProduct(code);
        machine.setMachineState(new ProductSelectedState(machine));
    }

    @Override
    public void selectSlot(Slot slot) {
        if (slot.getProducts().size() == 0) {
            System.out.println("Slot " + slot.toString() + " is out of stock.");
            return;
        }
        machine.setSelectedSlot(slot);
        System.out.println("Slot " + slot.getId() + " selected from slot. You can now insert coins.");
        machine.setMachineState(new ProductSelectedState(machine));
    }
}
