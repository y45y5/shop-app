package com.github.shop_app.view;

import com.github.shop_app.controller.ProductPageController;
import com.github.shop_app.main.Main;
import com.github.shop_app.model.ActiveUser;
import com.github.shop_app.model.Product;
import com.github.shop_app.model.User;
import com.github.shop_app.utility.UserSession;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager {
    private static Stage mainStage;

    public static void setMainStage(Stage stage){
        SceneManager.mainStage = stage;
        windowConfig();
    }

    public static void setStartingScene(){ SceneManager.mainStage.setScene(loadScene("startWindow")); }
    public static void setHomeScene(){ SceneManager.mainStage.setScene(loadScene("homeWindow")); }
    public static void setSettingsScene(){ SceneManager.mainStage.setScene(loadScene("settingsWindow")); }
    public static void setSearchScene(){ SceneManager.mainStage.setScene(loadScene("searchWindow")); }
    public static void setCartScene(){ SceneManager.mainStage.setScene(loadScene("cartWindow")); }
    public static void setProductScene(Product product){ SceneManager.mainStage.setScene(loadScene(product)); }

    public static void setActiveUser(User user){ ActiveUser.setActiveUser(user); }

    private static Scene loadScene(String fxmlFile){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        try{ Pane pane = fxmlLoader.load(); return new Scene(pane); }
        catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    private static Scene loadScene(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/fxml/productWindow.fxml"));
        try{
            Pane pane = fxmlLoader.load();
            ProductPageController productPageController = fxmlLoader.getController();
            productPageController.setProduct(product);
            return new Scene(pane);
        }
        catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    private static void windowConfig(){
        SceneManager.mainStage.setTitle("Shop");
        SceneManager.mainStage.setResizable(false);

        SceneManager.mainStage.setOnCloseRequest(e -> {
            Platform.exit();
            UserSession.session.close();
            System.exit(0);
        });

        setStartingScene();
        SceneManager.mainStage.show();
    }
}
