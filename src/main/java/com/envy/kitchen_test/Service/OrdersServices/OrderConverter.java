package com.envy.kitchen_test.Service.OrdersServices;

import com.envy.kitchen_test.Model.*;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class OrderConverter {
    public static Order getOrderById(int id){
        System.out.println("OrderConverter " + Thread.currentThread().getName());
        Dish dish;
        ArrayList<Ingredient> ingredients;
        try(Session session = ConnectionService.getSessionFactory().openSession()){
            Query<Dish> query = session.createQuery("from Dish where id = :id", Dish.class);
            query.setParameter("id", id);
            dish = query.uniqueResult();
            NativeQuery<Ingredient> query2 = session.createNativeQuery("""
                    SELECT ingredients.* FROM ingredients
                    JOIN dishes_ingredients ON ingredients.id = dishes_ingredients.ingredient_id
                    WHERE dish_id = :id
                    """, Ingredient.class);
            ingredients = (ArrayList<Ingredient>) query2.getResultList();
        }

        return new Order(dish, ingredients);
    }
}
