package org.example.state;

import org.example.Coffee;
import org.example.CoffeeVendingMachine;

public interface VendingMachineState {
    void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee);
    void cancel(CoffeeVendingMachine coffeeVendingMachine);
    void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine);
    void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount);
}
