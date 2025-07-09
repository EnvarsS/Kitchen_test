package com.envy.kitchen_test.Fabric;

import com.envy.kitchen_test.Model.Dish;
import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrdersFormatterFabric {

    private static final ConcurrentHashMap<HashSet<Ingredient>, Dish> dish_ingredients = new ConcurrentHashMap<>();

    public static synchronized Order getOrderByIngredients(ArrayList<Ingredient> ingredients) {
        System.out.println("Input ingredients: " + ingredients);

        // Manual comparison by ingredient IDs
        Set<Integer> inputIngredientIds = ingredients.stream()
            .map(Ingredient::getId)
            .collect(Collectors.toSet());

        System.out.println("Input ingredient IDs: " + inputIngredientIds);

        for (Map.Entry<HashSet<Ingredient>, Dish> entry : dish_ingredients.entrySet()) {
            Set<Integer> dishIngredientIds = entry.getKey().stream()
                .map(Ingredient::getId)
                .collect(Collectors.toSet());

            System.out.println("Comparing with dish: " + entry.getValue().getName() +
                             " IDs: " + dishIngredientIds);

            if (inputIngredientIds.equals(dishIngredientIds)) {
                System.out.println("Match found! Dish: " + entry.getValue().getName());
                return new Order(entry.getValue(), entry.getKey());
            }
        }

        System.out.println("No matching dish found");
        return null;
    }

    public static void initialize() {
        try (Session session = ConnectionService.getSessionFactory().openSession()) {
            // Get all dishes
            NativeQuery<Integer> dishIdsQuery = session.createNativeQuery("SELECT id FROM dishes", Integer.class);
            List<Integer> dishIds = dishIdsQuery.getResultList();

            for (Integer dishId : dishIds) {
                NativeQuery<Dish> dishQuery = session.createNativeQuery("SELECT * FROM dishes WHERE id = :id", Dish.class);
                dishQuery.setParameter("id", dishId);
                Dish dish = dishQuery.uniqueResult();

                NativeQuery<Ingredient> ingredientsQuery = session.createNativeQuery("""
                        SELECT ingredients.* FROM ingredients
                        JOIN dishes_ingredients di on ingredients.id = di.ingredient_id
                        WHERE dish_id = :id
                        ORDER BY ingredients.id
                        """, Ingredient.class);
                ingredientsQuery.setParameter("id", dishId);
                HashSet<Ingredient> ingredients = (HashSet<Ingredient>) ingredientsQuery.getResultStream().collect(Collectors.toSet());

                dish_ingredients.put(ingredients, dish);
            }
        }
    }
}
