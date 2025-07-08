package com.envy.kitchen_test.Service.OrdersServices;

import com.envy.kitchen_test.Controller.KitchenController;
import com.envy.kitchen_test.Model.Order;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class OrderEngine implements Runnable {

    private static final ExecutorService ordersListExecutor = Executors.newFixedThreadPool(5, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    private static final OrdersGeneratorService ordersGeneratorService = new OrdersGeneratorService();

    private HBox parentHBox;

    public OrderEngine(HBox parentHBox) {
        this.parentHBox = parentHBox;
    }

    @Override
    public void run() {
        while (true) {
            Order order = ordersGeneratorService.generateRandomOrder();

            ordersListExecutor.submit(new FormattedOrder(parentHBox, order));

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
