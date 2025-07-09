package com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.BoardServices.BoardListService;
import com.envy.kitchen_test.Service.GameStatisticServices.CountingStatistic;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class OrdersListService {
    private static OrdersListService instance;

    public static OrdersListService getInstance() {
        if (instance == null) {
            instance = new OrdersListService();
        }
        return instance;
    }

    private final ConcurrentHashMap<Order, Future<?>> runningOrders = new ConcurrentHashMap<>(5);

    public void addRunningOrder(Order order, Future<?> future) {
        synchronized (runningOrders) {
            runningOrders.put(order, future);
        }
    }

    public ConcurrentHashMap<Order, Future<?>> getRunningOrders() {
        return runningOrders;
    }

    public void deleteRunningOrder(Order order) {
        System.out.println("Deleting order " + order);
        synchronized (runningOrders) {
            if (order == null) {
                System.out.println("order is null. There's no dish with this receipt");
                return;
            }

            // Atomically remove and get the future
            Future<?> future = runningOrders.remove(order);
            System.out.println("Future " + future);

            if (future == null) {
                System.out.println("Order already completed or not found: " + order.toString());
                return;
            }

            try {
                boolean isCanceled = future.cancel(true);
                System.out.println("Order canceled successfully: " + order.getDishName());
                if (isCanceled) {
                    CountingStatistic.getInstance().increment();
                    CountingStatistic.getInstance().updateCounter();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Order: " + order.toString() + " failed");
            }
        }
    }
}
