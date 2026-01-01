package org.example.state;

import org.example.Coffee;
import org.example.CoffeeVendingMachine;
import org.example.enums.CoffeeType;

public class OutOfIngredientState implements VendingMachineState {

    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        System.out.println("Machine is out of ingredients. Cannot select coffee.");
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Refunding money: " + coffeeVendingMachine.getMoneyInserted());
        coffeeVendingMachine.reset();
        coffeeVendingMachine.setState(new ReadyState());
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        System.out.println("Machine is out of ingredients. Cannot dispense coffee.");
    }

    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        System.out.println("Machine is out of ingredients. Cannot accept money.");
    }
}
