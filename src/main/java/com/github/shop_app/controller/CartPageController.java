package com.github.shop_app.controller;

import com.github.shop_app.dao.UserDAO;
import com.github.shop_app.model.ActiveUser;
import com.github.shop_app.model.Product;
import com.github.shop_app.utility.GenerateProductInfo;
import com.github.shop_app.view.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class CartPageController extends MenuController implements IMenuController {

    @FXML private Label loadingScreen;
    @FXML private GridPane mainGrid;

    @FXML void initialize(){
        Platform.runLater(() -> {
            fillWithProducts();
            fadeOut();
        });
    }

    private void fillWithProducts(){
        mainGrid.getChildren().clear();
        List<Product> productList = ActiveUser.getUser().getProductList();
        if(!productList.isEmpty()){
            int column = 0; int row = 0;
            for (Product product : productList) {
                if(column > 1){
                    row++;
                    if(mainGrid.getRowCount() < row) mainGrid.addRow(row);
                    column = 0;
                }
                mainGrid.add(GenerateProductInfo.generateProduct(product), column, row);
                column++;
            }
        }
    }

    @FXML private void onPlaceOrderAction(){
        ActiveUser.getUser().setProductList(new ArrayList<>());
        UserDAO userDAO = new UserDAO(ActiveUser.getUser());

        userDAO.updateUser();
        SceneManager.setCartScene();
    }

    @FXML private void onDeleteAllAction(){
        ActiveUser.getUser().setProductList(new ArrayList<>());
        UserDAO userDAO = new UserDAO(ActiveUser.getUser());
        userDAO.updateUser();
        SceneManager.setCartScene();
    }

    @Override
    public void onLogOutAction() { logOutAction(); }

    @Override
    public void onSettingsAction() { settingsAction(); }

    @Override
    public void onHomeAction() { homeAction(); }

    @Override
    public void onSearchAction() { searchAction(); }

    @Override
    public void onYourCartAction() {  }

    @Override
    public void fadeOut() { fadeOutAction(loadingScreen); }
}
