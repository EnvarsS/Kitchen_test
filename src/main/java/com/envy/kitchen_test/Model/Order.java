package com.envy.kitchen_test.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Order {

    private static AtomicInteger idCounter = new AtomicInteger(0);

    private int id;
    private Dish dish;
    private Set<Ingredient> ingredients;

    public Order(Dish dish, Set<Ingredient> ingredients) {
        this.id = idCounter.getAndIncrement();
        this.dish = dish;
        this.ingredients = ingredients;
    }

    public String getDishName() {
        return dish.getName();
    }

    public ArrayList<String> getIngredients() {
        return (ArrayList<String>) ingredients.stream().map(Ingredient::getName).collect(Collectors.toList());
    }

    public Set<Ingredient> getIngredientsAsSet() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        // Remove id from comparison - only compare dish and ingredients
        return Objects.equals(dish, order.dish) && Objects.equals(ingredients, order.ingredients);
    }

    @Override
    public int hashCode() {
        // Remove id from hash code calculation
        return Objects.hash(dish, ingredients);
    }
}
