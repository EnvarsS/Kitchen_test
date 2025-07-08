package com.envy.kitchen_test.Service.UtilServices;

import javafx.stage.Stage;

public class StageService {
    private static StageService instance;
    private Stage stage;

    public static StageService getInstance() {
        if (instance == null) {
            instance = new StageService();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage(){
        return stage;
    }
}
