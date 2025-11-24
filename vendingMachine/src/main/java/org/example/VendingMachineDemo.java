package org.example;

import org.example.entities.Product;
import org.example.entities.Slot;
import org.example.enums.Coin;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        // Add products to the inventory
        Slot slotOne = new Slot(10D, 9D, 8D, null, 10);
        vendingMachine.addSlot(slotOne);
        List<Product> products = new ArrayList<>();
        products.add(new Product("A1", "Coke", 40, 9D, 8D, 7D));
        vendingMachine.restockProduct(products, slotOne);

        // Select a product
        System.out.println("\n--- Step 1: Select an item ---");
        vendingMachine.setSelectedProduct("A1");

        // Insert coins
        System.out.println("\n--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.NICKEL); // 5

        // Dispense the product
        System.out.println("\n--- Step 3: Dispense item ---");
        vendingMachine.dispenseProduct(); // Should dispense Coke

        // Select another item
        System.out.println("\n--- Step 4: Select another item ---");
        vendingMachine.setSelectedProduct("B1");

        // Insert more amount
        System.out.println("\n--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Coin.QUARTER); // 25

        // Try to dispense the product
        System.out.println("\n--- Step 6: Dispense and return change ---");
        vendingMachine.dispenseProduct();
    }
}
