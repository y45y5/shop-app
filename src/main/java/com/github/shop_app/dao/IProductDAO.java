package com.github.shop_app.dao;

import com.github.shop_app.model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> getProductList();
    void saveNewProduct(Product product);
}
