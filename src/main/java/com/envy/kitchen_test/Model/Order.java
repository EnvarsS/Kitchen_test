package com.envy.kitchen_test.Model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Order {

    private static AtomicInteger idCounter = new AtomicInteger(0);

    private int id;
    private Dish dish;
    private HashSet<Ingredient> ingredients;

    public Order(Dish dish, HashSet<Ingredient> ingredients) {
        id = idCounter.incrementAndGet();
        this.dish = dish;
        this.ingredients = ingredients;
    }

    public String getDishName() {
        return dish.getName();
    }

    public ArrayList<String> getIngredients() {
        return (ArrayList<String>) ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
    }

    public HashSet<Ingredient> getIngredientsAsSet() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id; // Compare by unique ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash by unique ID
    }
}
