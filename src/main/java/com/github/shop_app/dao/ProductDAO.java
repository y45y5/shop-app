package com.github.shop_app.dao;

import com.github.shop_app.model.Product;

import java.util.List;

public class ProductDAO extends DAO<Product> implements IProductDAO{

    @Override
    public List<Product> getProductList(){
        return getObjectList(Product.class);
    }

    @Override
    public void saveNewProduct(Product product) {
        saveObject(product);
    }
}
