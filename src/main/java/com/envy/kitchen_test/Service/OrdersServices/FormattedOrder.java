package com.envy.kitchen_test.Service.OrdersServices;

import com.envy.kitchen_test.Model.Order;
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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(() -> parentHBox.getChildren().remove(orderView));
    }
}
