package com.envy.kitchen_test.Service.IngredientsServices;

import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Service.BoardServices.BoardListService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class IngredientChooseHandleService {

    private static IngredientChooseHandleService instance;

    public static IngredientChooseHandleService getInstance() {
        if (instance == null) {
            instance = new IngredientChooseHandleService();
        }
        return instance;
    }

    public IngredientChooseHandleService() {
    }

    public void initializeButtons(ArrayList<VBox> ingredientsVBoxes, VBox boardVBox) {
        for (int i = 0; i < ingredientsVBoxes.size(); i++) {
            Button button = (Button) ingredientsVBoxes.get(i).getChildren().get(1);
            System.out.println(button);

            ScrollPane board = (ScrollPane) boardVBox.getChildren().get(1);

            int ingredientIndex = i + 1;

            button.setOnAction(e -> {
                Ingredient ingredient = IngredientGetter.getIngredient(ingredientIndex);
                boolean isAdded = BoardListService.getInstance().addIngredientToList(ingredient);
                if(isAdded) {
                    Label ingredientLabel = new Label(ingredient.getName());
                    ingredientLabel.getStylesheets().add(IngredientChooseHandleService.class.getResource("/com/envy/kitchen_test/style/OrderMenuStyle.css").toExternalForm());
                    ingredientLabel.getStyleClass().add("ingredient");

                    VBox container = (VBox) board.getContent(); // ensure content is VBox
                    container.getChildren().add(ingredientLabel);
                }

            });
        }
    }
}
