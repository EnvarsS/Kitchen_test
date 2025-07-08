package com.envy.kitchen_test.Service.OrdersServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.ui_elements.OrderView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class OrdersGeneratorService {
    public synchronized Order generateRandomOrder() {
        return OrderConverter.getOrderById(getRandomDishId());
    }

    private static int getRandomDishId() {
        ArrayList<Integer> dishesId;
        try (Session session = ConnectionService.getSessionFactory().openSession()) {
            NativeQuery<Integer> query = session.createNativeQuery("SELECT id FROM dishes", Integer.class);
            dishesId = (ArrayList<Integer>) query.getResultList();
        }
        return dishesId.get(ThreadLocalRandom.current().nextInt(dishesId.size()));
    }


}
