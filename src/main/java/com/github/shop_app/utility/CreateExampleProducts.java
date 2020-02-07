package com.github.shop_app.utility;

import com.github.shop_app.dao.ProductDAO;
import com.github.shop_app.model.Product;

import java.util.Random;

public class CreateExampleProducts {

    public static void createProducts(int productsNumber){
        ProductDAO productDAO = new ProductDAO();
        for(int i = 0; i < productsNumber; i++){
            productDAO.saveNewProduct(newProduct());
        }
    }

    private static Product newProduct(){
        String[] itemPrefix = {"Samsung", "LG", "Huawei", "Xiaomi", "Motorola", "Acer", "Asus", "Blackberry", "Gigabyte", "Google"};
        String[] itemMainName = {"Galaxy", "Redmi", "Pixel", "Evolve", "Liquid", "Predator", "Aquos", "Revvl", "Mi", "Shark"};
        String[] itemSuffix = {"2", "3", "A20", "Z90", "Z70", "S5", "P", "U6", "Y4", "R4", "A3", "I8"};
        String[] itemSuffix2 = {"Pro", "Premium", "Go", "Mini", "Plus", "High", "Hyper"};
        String[] categories = {"Smartphone", "Smartwatch", "Tablet"};
        String[] imageUrl = {"exampleProduct1.png", "exampleProduct2.png", "exampleProduct3.png", "exampleProduct4.png"};
        double[] prices = {49.99, 59.99, 69.99, 79.99, 89.99, 99.99, 50.99, 70.99};
        float[] promotions = {0, 0, 0, 0, (float) 0.1, (float) 0.2, (float) 0.3, (float) 0.4};

        Product product = new Product();
        Random random = new Random();
        product.setName(
                itemPrefix[random.nextInt(itemPrefix.length)] + " " +
                itemMainName[random.nextInt(itemMainName.length)] + " " +
                itemSuffix[random.nextInt(itemSuffix.length)] + " " +
                itemSuffix2[random.nextInt(itemSuffix2.length)]);
        product.setCategory(categories[random.nextInt(categories.length)]);
        product.setShort_info("Short info about " + product.getName() + ".");
        product.setPrice(prices[random.nextInt(prices.length)]);
        product.setPromotion(promotions[random.nextInt(promotions.length)]);
        product.setBought(random.nextInt(300));
        product.setPopular(product.getBought() > 150);
        product.setImageurl(imageUrl[random.nextInt(imageUrl.length)]);
        return product;
    }
}
