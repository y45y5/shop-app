package com.github.shop_app.controller;

import com.github.shop_app.model.Product;
import com.github.shop_app.utility.GetList;
import com.github.shop_app.view.SceneManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class ProductBoxController {

    private List<Product> productList = GetList.fetchProductList();

    @FXML
    private void onProductClickAction(Event evt){
        List<Node> elements = ((GridPane) evt.getSource()).getChildren();
        Label id = (Label) elements.get(4);
        String product_id = id.getText();
        for (Product product : productList)
            if (product_id.equals(String.valueOf(product.getProduct_id()))) SceneManager.setProductScene(product);
    }
}
