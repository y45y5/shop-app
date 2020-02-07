package com.github.shop_app.controller;

import com.github.shop_app.dao.UserDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class ProductPageController extends MenuController implements IMenuController{

    @FXML private Label productInfo;
    @FXML private Label productTitle;
    @FXML private ImageView productImage;
    @FXML private Label productPrice;
    @FXML private Label loadingScreen;

    @FXML private GridPane product1;
    @FXML private GridPane product2;

    private Product product;
    private List<Product> productList = GetList.fetchProductList();

    @FXML void initialize(){
        Platform.runLater(() -> {
            productInfo.setWrapText(true);
            setProductPage();
            setOtherProducts();
            fadeOut();
        });
    }

    private void setProductPage(){
        productTitle.setText(product.getName());
        DecimalFormat df2 = new DecimalFormat("#.##");
        float promotionPercent = 1 - product.getPromotion();
        productPrice.setText(df2.format(product.getPrice() * promotionPercent) + " $");
        productInfo.setText("Category: " + product.getCategory() + "\n\n" + product.getShort_info());
        productImage.setImage(new Image("img/example/" + product.getImageurl()));
    }

    @FXML private void onProductClickAction(Event evt){
        List<Node> elements = ((GridPane) evt.getSource()).getChildren();
        Label id = (Label) elements.get(4);
        String product_id = id.getText();
        for (Product product : productList)
            if (product_id.equals(String.valueOf(product.getProduct_id()))) SceneManager.setProductScene(product);
    }

    private void setOtherProducts(){
        int number1 = new Random().nextInt(productList.size());
        int number2 = RandomNumber.randomNumbers(number1, number1, productList.size());

        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product1), productList.get(number1));
        GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(product2), productList.get(number2));
    }

    public void setProduct(Product product){ this.product = product; }

    @FXML private void onAddToCartAction(){
        ActiveUser.getUser().addToCart(product);
        UserDAO userDAO = new UserDAO(ActiveUser.getUser());
        userDAO.updateUser();
    }

    @Override
    @FXML public void onLogOutAction() { logOutAction(); }

    @Override
    @FXML public void onSettingsAction() { settingsAction(); }

    @Override
    @FXML public void onHomeAction() { homeAction(); }

    @Override
    @FXML public void onSearchAction() { searchAction(); }

    @Override
    @FXML public void onYourCartAction() { yourCartAction(); }

    @Override
    public void fadeOut() { fadeOutAction(loadingScreen); }
}
