package com.envy.kitchen_test.Service.OrdersServices;

import java.util.HashMap;

public class OrdersListService {
    private static OrdersListService instance;

    public static OrdersListService getInstance() {
        if (instance == null) {
            instance = new OrdersListService();
        }
        return instance;
    }

    private final HashMap<> ordersList = new HashMap();


}
