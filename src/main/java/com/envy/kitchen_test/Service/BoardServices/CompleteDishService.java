package com.envy.kitchen_test.Service.BoardServices;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CompleteDishService {
    private static CompleteDishService instance;

    public CompleteDishService(ScrollPane ingredientListScrollPane) {
        if (instance == null) {
            instance = new CompleteDishService();
            instance.IngredientListScrollPane = ingredientListScrollPane;
        }
    }

    private CompleteDishService() {
    }

    public static CompleteDishService getInstance() {
        return instance;
    }

    private ScrollPane IngredientListScrollPane;

    public void initiateDishSubmitButton(VBox boardVBox) {
        Button boardSubmitButton = (Button) boardVBox.getChildren().get(2);

        boardSubmitButton.setOnAction(e -> {

        });
    }

}
