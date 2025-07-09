package com.envy.kitchen_test.Service.BoardServices;

import com.envy.kitchen_test.Fabric.OrdersFormatterFabric;
import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Model.Order;
import com.envy.kitchen_test.Service.OrdersServices.OrdersCompletingServices.OrdersListService;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CompleteDishService {
    private static CompleteDishService instance;

    public static CompleteDishService getInstance() {
        if (instance == null) {
            instance = new CompleteDishService();
        }
        return instance;
    }

    public void initiateDishSubmitButton(VBox boardVBox) {
        Button boardSubmitButton = (Button) boardVBox.getChildren().get(2);
        ScrollPane boardScrollPane = (ScrollPane) boardVBox.getChildren().get(1);

        boardSubmitButton.setOnAction(e -> {
            ArrayList<Ingredient> boardIngredients = (ArrayList<Ingredient>) BoardListService.getInstance().getIngredients().stream().collect(Collectors.toList());
            System.out.println("Board - " + boardIngredients);
            VBox container = (VBox) boardScrollPane.getContent();
            container.getChildren().clear();
            BoardListService.getInstance().clearIngredients();

            Order currentOrder = OrdersFormatterFabric.getOrderByIngredients(boardIngredients);

            OrdersListService.getInstance().deleteRunningOrder(currentOrder);
        });
    }

}
