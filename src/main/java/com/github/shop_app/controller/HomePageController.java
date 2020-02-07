package com.github.shop_app.controller;

import com.github.shop_app.model.ActiveUser;
import com.github.shop_app.model.Product;
import com.github.shop_app.utility.GenerateProductInfo;
import com.github.shop_app.utility.GetList;
import com.github.shop_app.utility.RandomNumber;
import com.github.shop_app.view.SceneManager;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePageController extends MenuController implements IMenuController{

    @FXML private Label welcomeBack;
    @FXML private Label loadingScreen;
    @FXML private GridPane product1;
    @FXML private GridPane product2;
    @FXML private GridPane product3;
    @FXML private GridPane product4;
    @FXML private GridPane product5;
    @FXML private GridPane product6;

    private List<Product> productList = GetList.fetchProductList();
    private List<Product> popularProductList = fetchPopularProductList();

    @FXML void initialize(){
        Platform.runLater(() -> {
            welcomeBack.setText(ActiveUser.getActiveUsername() + "!");
            setNewProducts();
            setPopularProducts();
            setOtherProducts();
            fadeOut();
        });
    }

    private void setNewProducts(){
        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product1), productList.get(productList.size()-1));
        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product2), productList.get(productList.size()-2));
    }

    private void setPopularProducts(){
        int number1 = new Random().nextInt(popularProductList.size());
        int number2 = RandomNumber.randomNumbers(number1, number1, popularProductList.size());

        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product3), popularProductList.get(number1));
        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product4), popularProductList.get(number2));
    }

    private void setOtherProducts(){
        int number1 = new Random().nextInt(productList.size());
        int number2 = RandomNumber.randomNumbers(number1, number1, productList.size());

        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product5), productList.get(number1));
        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product6), productList.get(number2));
    }

    private List<Product> fetchPopularProductList(){
        List<Product> popularProducts = new ArrayList<>();
        for (Product product : productList) if (product.isPopular()) popularProducts.add(product);
        return popularProducts;
    }

    @FXML private void onProductClickAction(Event evt){
        List<Node> elements = ((GridPane) evt.getSource()).getChildren();
        Label id = (Label) elements.get(4);
        String product_id = id.getText();
        for (Product product : productList)
            if (product_id.equals(String.valueOf(product.getProduct_id()))) SceneManager.setProductScene(product);
    }

    @Override
    public void fadeOut(){ fadeOutAction(loadingScreen); }

    @Override
    @FXML public void onLogOutAction() { logOutAction(); }

    @Override
    @FXML public void onSettingsAction() { settingsAction(); }

    @Override
    @FXML public void onHomeAction() {  }

    @Override
    @FXML public void onSearchAction() { searchAction(); }

    @Override
    @FXML public void onYourCartAction() { yourCartAction(); }
}
