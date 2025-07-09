package com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.BoardServices.BoardListService;
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
    if(order == null) {
        System.out.println("order is null. There's no dish with this receipt");
        return;
    }

    // Atomically remove and get the future
    Future<?> future = runningOrders.remove(order);

    if (future == null) {
        System.out.println("Order already completed or not found: " + order.toString());
        return;
    }

    // Only clear ingredients if this thread successfully removed the order
    BoardListService.getInstance().clearIngredients();

    try {
        future.cancel(true);
        CountingStatistic.getInstance().increment();
        System.out.println("Order completed successfully: " + order.toString());
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Order: " + order.toString() + " failed");
    }
}
}
