package com.github.shop_app.utility;

import com.github.shop_app.dao.ProductDAO;
import com.github.shop_app.dao.UserDAO;
import com.github.shop_app.model.Product;
import com.github.shop_app.model.User;

import java.util.List;

public class GetList {
    private static UserDAO userDAO = new UserDAO();
    private static ProductDAO productDAO = new ProductDAO();

    public static List<User> fetchUserList(){
        return userDAO.getUserList();
    }

    public static List<Product> fetchProductList(){
        return productDAO.getProductList();
    }
}
