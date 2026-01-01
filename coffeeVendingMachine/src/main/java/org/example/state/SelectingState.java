package org.example.state;

import org.example.Coffee;
import org.example.CoffeeVendingMachine;
import org.example.enums.CoffeeType;

public class SelectingState implements VendingMachineState{
    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffeeType) {
        System.out.println("Coffee already selected: " + coffeeType);
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Please insert money first.");
    }

    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        coffeeVendingMachine.setMoneyInserted(machine.getMoneyInserted() + amount);
        System.out.println("Inserted money: " + amount + ". Total inserted: " + coffeeVendingMachine.getMoneyInserted());
        if (coffeeVendingMachine.getMoneyInserted() >= coffeeVendingMachine.getSelectedCoffee().getPrice()) {
            coffeeVendingMachine.setState(new PaidState());
        } else {
            System.out.println("Please insert more money.");
        }
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Coffee cancelled, Refunding money: " + coffeeVendingMachine.getMoneyInserted());
        coffeeVendingMachine.reset();
        coffeeVendingMachine.setState(new ReadyState());
    }
}
