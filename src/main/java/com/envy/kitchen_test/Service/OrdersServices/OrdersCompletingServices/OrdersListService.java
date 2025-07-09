package com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.GameStatisticServices.CountingStatistic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

public class OrdersListService {
    private static OrdersListService instance;

    public static OrdersListService getInstance() {
        if (instance == null) {
            instance = new OrdersListService();
        }
        return instance;
    }

    private final ConcurrentHashMap<Order, Future<?>> runningOrders = new ConcurrentHashMap<>();

    public void addRunningOrder(Order order, Future<?> future) {
        runningOrders.put(order, future);
    }

    public ConcurrentHashMap<Order, Future<?>> getRunningOrders() {
        return runningOrders;
    }

    public void deleteRunningOrder(Order order) {

        System.out.println("Deleting order " + order);
        Future<?> future = runningOrders.remove(order);

        System.out.println(future);
        try {
            future.cancel(true);
            CountingStatistic.getInstance().increment();
        }
        catch (Exception e) {
            e.printStackTrace();

            System.out.println("Order: " + order.toString() + " failed");
        }
    }
}
