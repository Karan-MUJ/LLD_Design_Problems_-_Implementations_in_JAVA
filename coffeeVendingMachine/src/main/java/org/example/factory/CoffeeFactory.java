package org.example.factory;

import org.example.Coffee;
import org.example.enums.CoffeeType;
import org.example.templatemethod.Cappuccino;
import org.example.templatemethod.Espresso;
import org.example.templatemethod.Latte;

public class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType coffeeType) {
        switch (coffeeType) {
            case ESPRESSO -> new Espresso();
            case LATTE -> new Latte();
            case CAPPUCCINO -> new Cappuccino();
        }
    }
}
