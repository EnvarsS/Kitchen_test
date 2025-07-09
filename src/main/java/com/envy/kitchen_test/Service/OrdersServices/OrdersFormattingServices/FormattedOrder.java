package com.envy.kitchen_test.Service.OrdersServices.OrdersFormattingServices;

import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices.OrdersListService;
import com.envy.kitchen_test.ui_elements.OrderView;
import javafx.application.Platform;
import javafx.scene.layout.HBox;

public class FormattedOrder implements Runnable {
    private final HBox parentHBox;
    private final Order order;

    public FormattedOrder(HBox parentHBox, Order order) {
        this.parentHBox = parentHBox;
        this.order = order;
    }

    @Override
    public void run() {
        OrderView orderView = new OrderView(this.order);
        Platform.runLater(() -> parentHBox.getChildren().add(orderView));

        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Platform.runLater(() -> parentHBox.getChildren().remove(orderView));
            OrdersListService.getInstance().deleteRunningOrder(order);
        }
        Platform.runLater(() -> parentHBox.getChildren().remove(orderView));
        OrdersListService.getInstance().deleteRunningOrder(order);
    }
}
