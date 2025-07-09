package com.envy.kitchen_test.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Order {

    private static AtomicInteger idCounter = new AtomicInteger(0);

    private int id;
    private Dish dish;
    private ArrayList<Ingredient> ingredients;

    public Order(Dish dish, ArrayList<Ingredient> ingredients) {
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

    public ArrayList<Ingredient> getIngredientsAsList() {
        return ingredients;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(dish, order.dish) && Objects.equals(ingredients, order.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish, ingredients);
    }

    @Override
    public String toString() {
        return "Order{" +
               "dish=" + dish +
               ", ingredients=" + ingredients +
               '}';
    }
}
