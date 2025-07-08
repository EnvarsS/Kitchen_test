package com.envy.kitchen_test.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Order {
    private Dish dish;
    private ArrayList<Ingredient> ingredients;

    public Order(Dish dish, ArrayList<Ingredient> ingredients) {
        this.dish = dish;
        this.ingredients = ingredients;
    }

    public String getDishName() {
        return dish.getName();
    }

    public ArrayList<String> getIngredients() {
        return (ArrayList<String>) ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
    }
}
