package com.github.shop_app.controller;

import com.github.shop_app.model.Product;
import com.github.shop_app.utility.GenerateProductInfo;
import com.github.shop_app.utility.GetList;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SearchPageController extends MenuController implements IMenuController{

    @FXML private GridPane mainGrid;
    @FXML private TextField searchField;
    @FXML private Label loadingScreen;

    private List<Product> productList = GetList.fetchProductList();

    @FXML void initialize(){
        Platform.runLater(this::fadeOut);
    }

    @FXML
    private void onSearchButtonAction(){
        mainGrid.getChildren().clear();
        String search = searchField.getText();
        List<Product> searchList = new ArrayList<>();

        if(!search.isEmpty()){
            for (Product product : productList) {
                if(product.getName().toUpperCase().contains(search.toUpperCase())) searchList.add(product);
            }
            if(!searchList.isEmpty()){
                int column = 0; int row = 0;
                for (Product product : searchList) {
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
    }

    @Override
    @FXML public void onLogOutAction() { logOutAction(); }

    @Override
    @FXML public void onSettingsAction() { settingsAction(); }

    @Override
    @FXML public void onHomeAction() { homeAction(); }

    @Override
    @FXML public void onSearchAction() {  }

    @Override
    @FXML public void onYourCartAction() { yourCartAction(); }

    @Override
    public void fadeOut() { fadeOutAction(loadingScreen); }
}