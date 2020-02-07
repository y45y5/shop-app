package com.github.shop_app.main;

import com.github.shop_app.utility.CreateExampleProducts;
import com.github.shop_app.utility.UserSession;
import com.github.shop_app.view.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        UserSession.createSession();
        //CreateExampleProducts.createProducts(70);
        SceneManager.setMainStage(stage);
        SceneManager.setStartingScene();
        stage.show();
    }
}
