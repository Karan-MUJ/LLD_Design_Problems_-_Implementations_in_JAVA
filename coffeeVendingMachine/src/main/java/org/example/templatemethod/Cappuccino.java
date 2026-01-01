package org.example.templatemethod;

import org.example.Coffee;
import org.example.enums.CoffeeType;
import org.example.enums.Ingredient;

import java.util.Map;

public class Cappuccino extends Coffee {
    public Cappuccino() {
        this.coffeeType = CoffeeType.CAPPUCCINO.toString();
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding steamed milk and milk foam.");
    }

    @Override
    public int getPrice() {
        return 250;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(
                Ingredient.WATER, 50,
                Ingredient.COFFEE_BEANS, 7,
                Ingredient.MILK, 150
        );
    }
}
