package org.example.state;

import org.example.Coffee;
import org.example.CoffeeVendingMachine;
import org.example.enums.CoffeeType;

public class ReadyState implements VendingMachineState {
    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        System.out.println("Coffee selected: " + coffee);
        coffeeVendingMachine.setSelectedCoffee(coffee);
        coffeeVendingMachine.setState(new SelectingState());
    }

    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        System.out.println("Please select a coffee first.");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Please select a coffee and insert money first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("No transaction to cancel.");
    }
}
