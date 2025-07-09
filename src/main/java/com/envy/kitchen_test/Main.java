package com.envy.kitchen_test;

import com.envy.kitchen_test.Model.Ingredient;
import com.envy.kitchen_test.Service.OrdersServices.OrdersFormattingServices.OrderConverter;
import com.envy.kitchen_test.Service.UtilServices.ConnectionService;
import com.envy.kitchen_test.Service.UtilServices.StageService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ConnectionService.connect();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Kitchen-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        StageService.getInstance().setStage(stage);

        StageService.getInstance().getStage().setTitle("Kitchen");
        StageService.getInstance().getStage().setScene(scene);
        StageService.getInstance().getStage().setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}