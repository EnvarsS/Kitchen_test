package com.envy.kitchen_test.Service.OrdersServices.OrdersFormattingServices;

import com.envy.kitchen_test.Model.*;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderConverter {
    public static Order getOrderById(int id) {
        Dish dish;
        ArrayList<Ingredient> ingredients;
        try (Session session = ConnectionService.getSessionFactory().openSession()) {
            NativeQuery<Dish> query = session.createNativeQuery("SELECT * FROM Dishes WHERE id = :id", Dish.class);
            query.setParameter("id", id);
            dish = query.uniqueResult();

            System.out.println("Dish " + dish);
            NativeQuery<Ingredient> query2 = session.createNativeQuery("""
                    SELECT ingredients.* FROM ingredients
                    JOIN dishes_ingredients ON ingredients.id = dishes_ingredients.ingredient_id
                    WHERE dish_id = :id
                    """, Ingredient.class);
            query2.setParameter("id", id);
            ingredients = (ArrayList<Ingredient>) query2.getResultList();
        }
        return new Order(dish, ingredients);
    }
}
