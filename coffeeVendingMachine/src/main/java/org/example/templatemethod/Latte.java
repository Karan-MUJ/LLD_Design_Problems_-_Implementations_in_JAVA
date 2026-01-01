package org.example.templatemethod;

import org.example.Coffee;
import org.example.enums.CoffeeType;
import org.example.enums.Ingredient;

import java.util.Map;

public class Latte extends Coffee {

    public Latte() {
        this.coffeeType = CoffeeType.LATTE.toString();
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding steamed milk.");
    }

    @Override
    public int getPrice() {
        return 220;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(
                Ingredient.WATER, 30,
                Ingredient.COFFEE_BEANS, 190,
                Ingredient.COFFEE_BEANS, 7
        );
    }
}
