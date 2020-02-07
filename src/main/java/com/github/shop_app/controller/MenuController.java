package com.github.shop_app.controller;

import com.github.shop_app.model.ActiveUser;
import com.github.shop_app.view.SceneManager;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public abstract class MenuController {
    void fadeOutAction(Label node){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(700));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(e -> hideElement(node));
        fadeTransition.play();
    }

    void hideElement(Label node){
        node.setMaxSize(0, 0);
        node.setText("");
    }

    void logOutAction(){
        ActiveUser.setActiveUser(null);
        SceneManager.setStartingScene();
    }

    void settingsAction(){ SceneManager.setSettingsScene(); }

    void homeAction(){ SceneManager.setHomeScene(); }

    void searchAction(){ SceneManager.setSearchScene(); }

    void yourCartAction(){ SceneManager.setCartScene(); }
}
