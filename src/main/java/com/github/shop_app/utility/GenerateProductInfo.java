package com.github.shop_app.utility;

import com.github.shop_app.main.Main;
import com.github.shop_app.model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.util.List;

public class GenerateProductInfo {
    public static List<Node> createNodeList(GridPane gridPane){
        return gridPane.getChildren();
    }

    public static GridPane generateProduct(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/fxml/productBox.fxml"));
        GridPane gridPane = null;
        try{
            gridPane = fxmlLoader.load();
            GenerateProductInfo.setProductInfo(GenerateProductInfo.createNodeList(gridPane), product);
        }
        catch (Exception e){ e.printStackTrace(); }
        return gridPane;
    }

    public static void setProductInfo(List<Node> productElements, Product product){
        Label title = (Label) productElements.get(0);
        Label tags = (Label) productElements.get(1);
        Label price = (Label) productElements.get(2);
        ImageView image = (ImageView) productElements.get(3);
        Label id = (Label) productElements.get(4);

        image.setImage(new Image("img/example/" + product.getImageurl()));

        DecimalFormat df2 = new DecimalFormat("#.##");
        String tag = setTags(product.isPopular(), product.getPromotion());

        title.setText(product.getName());
        if(tag.equals("")){ tags.setVisible(false); }
        else tags.setText(tag);

        float promotionPercent = 1 - product.getPromotion();
        price.setText(df2.format(product.getPrice() * promotionPercent) + " $");

        id.setText(String.valueOf(product.getProduct_id()));
    }

    private static String setTags(boolean popular, float promotion){
        String tag = "";
        if(popular) { tag += "Popular"; }
        if(!popular && promotion != 0) { tag+="Promotion"; }
        else if(promotion != 0) { tag += " | Promotion"; }
        return tag;
    }
}
