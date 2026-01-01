package org.example;

import org.example.decorator.CaramelSyrupDecorator;
import org.example.decorator.ExtraSugarDecorator;
import org.example.enums.CoffeeType;
import org.example.enums.ToppingType;
import org.example.factory.CoffeeFactory;
import org.example.state.ReadyState;
import org.example.state.VendingMachineState;

import java.util.List;

public class CoffeeVendingMachine {
    private static CoffeeVendingMachine INSTANCE;
    private VendingMachineState vendingMachineState;
    private Coffee selectedCoffee;
    private int moneyInserted;

    private CoffeeVendingMachine() {
        this.vendingMachineState = new ReadyState();
        this.moneyInserted = 0;
    }

    public static CoffeeVendingMachine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoffeeVendingMachine();
        }
        return INSTANCE;
    }

    public void selectCoffee(CoffeeType coffeeType, List<ToppingType> toppings) {
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);
        for (ToppingType topping: toppings) {
            if (topping == ToppingType.EXTRA_SUGAR) {
                coffee = new ExtraSugarDecorator(coffee);
            } else if (topping == ToppingType.CARAMEL_SYRUP) {
                coffee = new CaramelSyrupDecorator(coffee);
            }
        }
        this.vendingMachineState.selectCoffee(this, coffee);
    }

    public void insertMoney(int money) {
        this.vendingMachineState.insertMoney(this, money);
    }

    public void dispenseCoffee() {
        this.vendingMachineState.dispenseCoffee(this);
    }

    public void cancel() {
        this.vendingMachineState.cancel(this);
    }

    // Getters and Setters
    public void setState(VendingMachineState state) {
        this.vendingMachineState = state;
    }
    public VendingMachineState getVendingMachineState() {
        return vendingMachineState;
    }
    public void setSelectedCoffee(Coffee selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }
    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }
    public void setMoneyInserted(int moneyInserted) {
        this.moneyInserted = moneyInserted;
    }
    public int getMoneyInserted() {
        return moneyInserted;
    }
    public void reset() {
        this.selectedCoffee = null;
        this.moneyInserted = 0;
    }
}
