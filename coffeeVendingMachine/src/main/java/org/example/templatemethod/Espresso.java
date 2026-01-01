package org.example.templatemethod;

import org.example.Coffee;
import org.example.enums.CoffeeType;
import org.example.enums.Ingredient;

import java.util.Map;

public class Espresso extends Coffee {

    public Espresso() {
        this.coffeeType = CoffeeType.ESPRESSO.toString();
    }

    @Override
    protected void addCondiments() {
        System.out.println("No condiments for Espresso.");
    }

    @Override
    public int getPrice() {
        return 150;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(
                Ingredient.WATER, 200,
                Ingredient.COFFEE_BEANS, 15
        );
    }
}
