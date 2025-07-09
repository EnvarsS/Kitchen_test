package com.envy.kitchen_test.Service.OrdersServices.OrdersFormattingServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices.OrdersListService;
import javafx.scene.layout.HBox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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

            Future<?> future =  ordersListExecutor.submit(new FormattedOrder(parentHBox, order));
            OrdersListService.getInstance().addRunningOrder(order, future);

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
