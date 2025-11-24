package org.example;

import org.example.entities.Inventory;
import org.example.entities.Product;
import org.example.entities.Slot;
import org.example.enums.Coin;
import org.example.state.VendingMachineState;
import org.example.strategy.LowQuantityFirstSlotStrategy;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private final static VendingMachine instance = new VendingMachine();
    private final Inventory inventory = new Inventory(new LowQuantityFirstSlotStrategy());
    private String selectedProduct;
    private VendingMachineState state;
    private Coin insertedCoin;
    Slot selectedSlot;
    List<Slot> slots = new ArrayList<>();

    public static VendingMachine getInstance() {
        if (instance == null) {
            return new VendingMachine();
        } else {
            return instance;
        }
    }

    public String getSelectedProduct() { return selectedProduct; }
    public void setSelectedProduct(String selectedProduct) { this.selectedProduct = selectedProduct; }
    public void setMachineState(VendingMachineState state) { this.state = state; }
    public Inventory getInventory() { return inventory; }
    public Coin getInsertedCoin() { return insertedCoin; }
    public void insertCoin(Coin insertedCoin) { this.insertedCoin = insertedCoin; }
    public void setSelectedSlot(Slot slot) { this.selectedSlot = slot; }
    public Slot getSelectedSlot() { return selectedSlot; }
    public void addSlot(Slot slot) { slots.add(slot); }
    public void removeSlot(Slot slot) { slots.remove(slot); }

    public void reset() {
        this.selectedProduct = null;
        insertedCoin = null;
    }

    public void findSlotForSelectedProduct(String itemCode) {
        setSelectedSlot(inventory.getSlotForProduct(itemCode));
    }

    public Product dispenseProduct() {
        return inventory.deductProduct(selectedSlot);
    }

    public void returnChange() {
        System.out.println("Please wait, returning change in progress.");
    }

    public void restockProduct(List<Product> products, Slot slot) {
        inventory.restock(products, slot);
    }
}
