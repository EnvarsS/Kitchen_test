package com.envy.kitchen_test.Service.IngredientsServices;

import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class IngredientGetter {
    public static Ingredient getIngredient(int id) {
        try(Session session = ConnectionService.getSessionFactory().openSession()){
            NativeQuery<Ingredient> query = session.createNativeQuery("select * from ingredients where id = :id", Ingredient.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }
}
