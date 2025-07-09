package com.envy.kitchen_test.Fabric;

import com.envy.kitchen_test.Model.Dish;
import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrdersFormatterFabric {

    private static ConcurrentHashMap<ArrayList<Ingredient>, Dish> dish_ingredients = new ConcurrentHashMap<>();

    public static Order getOrderByIngredients(ArrayList<Ingredient> ingredients) {
        Dish dish = dish_ingredients.get(ingredients);
        if(dish == null)
            return null;

        return new Order(dish, ingredients);
    }

    public static void initialize() {
        AtomicInteger counter = new AtomicInteger(1);
        try (Session session = ConnectionService.getSessionFactory().openSession()) {
            NativeQuery<Dish> query1 = session.createNativeQuery("SELECT * FROM dishes WHERE id = :id", Dish.class);
            query1.setParameter("id", counter.get());
            Dish dish = query1.uniqueResult();
            NativeQuery<Ingredient> query2 = session.createNativeQuery("""
                    SELECT ingredients.* FROM ingredients
                    JOIN dishes_ingredients di on ingredients.id = di.ingredient_id
                    WHERE dish_id = :id
                    """, Ingredient.class);
            query2.setParameter("id", counter.getAndIncrement());
            ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) query2.getResultList();
            dish_ingredients.put(ingredients, dish);
        }
    }
}
