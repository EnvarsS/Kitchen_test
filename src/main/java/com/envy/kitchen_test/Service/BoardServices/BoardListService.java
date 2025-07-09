package com.envy.kitchen_test.Service.BoardServices;

import com.envy.kitchen_test.Model.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoardListService {

    private static BoardListService instance;

    private final Set<Ingredient> ingredients = new HashSet<>();

    public static BoardListService getInstance() {
        if(instance == null) {
            instance = new BoardListService();
        }
        return instance;
    }

    public boolean addIngredientToList(Ingredient ingredient) {
        if(!this.checkIfIngredientExist(ingredient)) {
            return ingredients.add(ingredient);
        }
        return false;
    }
    private boolean checkIfIngredientExist(Ingredient ingredient) {
        return ingredients.stream().anyMatch(i -> i.getName().equals(ingredient.getName()));
    }

    public void clearIngredients() {
        ingredients.clear();
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

}
