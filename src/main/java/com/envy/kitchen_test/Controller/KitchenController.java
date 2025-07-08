package com.envy.kitchen_test.Controller;


import com.envy.kitchen_test.Service.IngredientsServices.IngredientChooseHandleService;
import com.envy.kitchen_test.Service.OrdersServices.OrderEngine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KitchenController implements Initializable {

    private static final ExecutorService ordersExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    @FXML
    private VBox BreadVBox;

    @FXML
    private VBox CheeseVBox;

    @FXML
    private VBox LetucceVBox;

    @FXML
    private VBox MeatVBox;

    @FXML
    private VBox TomatoVBox;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private HBox ordersHBox;

    @FXML
    private VBox BoardVBox;


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Kitchen view initialized!");

        ordersExecutor.submit(new OrderEngine(ordersHBox));

        ArrayList<VBox> vboxList = new ArrayList<>(List.of(TomatoVBox, BreadVBox, CheeseVBox, LetucceVBox, MeatVBox));
        IngredientChooseHandleService.getInstance().initializeButtons(vboxList, BoardVBox);


    }

}
