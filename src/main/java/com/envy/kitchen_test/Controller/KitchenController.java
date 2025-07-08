package com.envy.kitchen_test.Controller;


import com.envy.kitchen_test.Service.OrdersServices.OrderEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KitchenController implements Initializable {

    private static final ExecutorService ordersExecutor = Executors.newSingleThreadExecutor();

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private HBox ordersHBox;


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Kitchen view initialized!");

        ordersExecutor.submit(new OrderEngine(ordersHBox));

    }
}
