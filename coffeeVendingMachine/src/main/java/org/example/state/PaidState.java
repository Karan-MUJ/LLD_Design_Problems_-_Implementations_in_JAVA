package org.example.state;

import org.example.Coffee;
import org.example.CoffeeVendingMachine;
import org.example.Inventory;

public class PaidState implements VendingMachineState {
    @Override
    public void selectCoffee(CoffeeVendingMachine coffeeVendingMachine, Coffee coffee) {
        System.out.println("Coffee already selected: " + coffee);
    }

    @Override
    public void cancel(CoffeeVendingMachine coffeeVendingMachine) {
        new SelectingState().cancel(coffeeVendingMachine);
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachine coffeeVendingMachine) {
        Inventory inventory = Inventory.getInstance();
        Coffee coffeeToDispense = coffeeVendingMachine.getSelectedCoffee();
        if (!inventory.hasIngredients(coffeeVendingMachine.getSelectedCoffee().getRecipe())) {
            System.out.println("Insufficient ingredients for " + coffeeToDispense.getCoffeeType() + ". Refunding money: " + coffeeVendingMachine.getMoneyInserted());
            coffeeVendingMachine.setState(new OutOfIngredientState());
            coffeeVendingMachine.getVendingMachineState().cancel(coffeeVendingMachine);
            return;
        }
        inventory.consumeIngredients(coffeeVendingMachine.getSelectedCoffee().getRecipe());
        coffeeToDispense.prepare();
        int change = coffeeVendingMachine.getMoneyInserted() - coffeeVendingMachine.getSelectedCoffee().getPrice();
        coffeeVendingMachine.reset();
        coffeeVendingMachine.setState(new ReadyState());
    }

    @Override
    public void insertMoney(CoffeeVendingMachine coffeeVendingMachine, int amount) {
        System.out.println("Money already inserted. Please wait for your coffee.");
    }
}
