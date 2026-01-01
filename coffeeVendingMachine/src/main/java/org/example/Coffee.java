package org.example;

import org.example.enums.Ingredient;

import java.util.Map;

public abstract class Coffee {
    protected String coffeeType = "Unknown Coffee";

    public String getCoffeeType() {
        return coffeeType;
    }

    public void prepare() {
        System.out.println("Preparing your " + this.getCoffeeType() + "...");
        grindBeans();
    }

    // Common steps
    private void grindBeans() {
        System.out.println("Grinding coffee beans.");
    }
    private void brew() {
        System.out.println("Brewing coffee with hot water.");
    }
    private void pourIntoCup() {
        System.out.println("Pouring coffee into cup.");
    }

    // Abstract step to be implemented by subclasses
    protected abstract void addCondiments();

    public abstract int getPrice();

    public abstract Map<Ingredient, Integer> getRecipe();
}
