package com.envy.kitchen_test.Service.OrdersServices;

import com.envy.kitchen_test.Controller.KitchenController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class OrderEngine implements Runnable {
    private static final HashMap<Thread, VBox> VBOX_MAP = new HashMap<>();

    private static final ExecutorService ordersListExecutor = Executors.newFixedThreadPool(5);

    private static final OrdersGeneratorService ordersGeneratorService = new OrdersGeneratorService();

    private HBox parentHBox;

    public OrderEngine(HBox parentHBox) {
        this.parentHBox = parentHBox;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("OrderEngine " + Thread.currentThread().getName());
            ordersGeneratorService.generateRandomOrder(parentHBox);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(4, 8));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ExecutorService getOrderListExecutor() {
        return ordersListExecutor;
    }
}
