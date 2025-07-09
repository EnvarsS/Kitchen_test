package com.envy.kitchen_test.ui_elements;

import com.envy.kitchen_test.Service.IngredientsServices.IngredientGetter;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import com.envy.kitchen_test.Model.*;

import java.util.HashSet;
import java.util.Objects;

public class OrderView extends VBox {
    private final Order order;

    public OrderView(Order order) {
        this.order = order;
        setSpacing(10);
        getStyleClass().add("order-box");

        getStylesheets().add(OrderView.class.getResource("/com/envy/kitchen_test/style/OrderMenuStyle.css").toExternalForm());

        Label nameLabel = new Label(order.getDishName());
        nameLabel.getStyleClass().add("dish-name");

        VBox ingredientsBox = new VBox(5);
        for (String ingredient : order.getIngredients()) {
            Label ing = new Label("• " + ingredient);
            ing.getStyleClass().add("ingredient");
            ingredientsBox.getChildren().add(ing);
        }
        setMaxHeight(50);

        getChildren().addAll(nameLabel, ingredientsBox);
    }
}
