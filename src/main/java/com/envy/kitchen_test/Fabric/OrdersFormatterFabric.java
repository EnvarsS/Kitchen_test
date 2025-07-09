package com.envy.kitchen_test.Fabric;

import com.envy.kitchen_test.Model.Dish;
import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class OrdersFormatterFabric {

    private static ConcurrentHashMap<Set<Ingredient>, Dish> dish_ingredients = new ConcurrentHashMap<>();

    public static Order getOrderByIngredients(Set<Ingredient> ingredients) {
        Dish dish = dish_ingredients.get(ingredients);
        if(dish == null)
            return null;

        return new Order(dish, ingredients);
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
            Set<Ingredient> ingredients = (Set<Ingredient>) ingredientsQuery.getResultStream().collect(Collectors.toSet());

            dish_ingredients.put(ingredients, dish);
        }
    }
}
}
