package org.example;

import org.example.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static Inventory INSTANCE;
    Map<Ingredient, Integer> stock = new HashMap<Ingredient, Integer>();

    private Inventory() {
    }

    public static Inventory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Inventory();
        }
        return INSTANCE;
    }

    public void addStock(Ingredient ingredient, int quantity) {
        stock.put(ingredient, quantity + stock.getOrDefault(ingredient, 0));
    }

    public boolean hasIngredients(Map<Ingredient, Integer> requiredIngredients) {
        return requiredIngredients.entrySet().stream().allMatch(entry -> stock.getOrDefault(entry.getKey(), 0) >= entry.getValue());
    }

    public synchronized void consumeIngredients(Map<Ingredient, Integer> requiredIngredients) {
        if (!hasIngredients(requiredIngredients)) {
            System.out.println("Not enough ingredients for " + requiredIngredients);
            return;
        }
        requiredIngredients.forEach((ingredient, quantity) -> {
            stock.put(ingredient, stock.get(ingredient) - quantity);
        });
    }

    public void displayStock() {
        System.out.println("Current Inventory Stock:");
        stock.forEach((ingredient, quantity) ->
            System.out.println(ingredient + ": " + quantity + " units"));
    }
}
