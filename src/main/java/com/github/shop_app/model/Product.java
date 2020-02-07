package com.github.shop_app.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String name;
    private double price;
    private String category;
    private String short_info;
    private float promotion;
    private int bought;
    private boolean popular;
    private String imageurl;

    public Product(){

    }

    public Product(int product_id, String name, double price, String category, String short_info,
                   float promotion, int bought, boolean popular, String imageurl) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.short_info = short_info;
        this.promotion = promotion;
        this.bought = bought;
        this.popular = popular;
        this.imageurl = imageurl;
    }

    public void setProduct_id(int product_id) { this.product_id = product_id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setShort_info(String short_info) { this.short_info = short_info; }
    public void setPromotion(float promotion) { this.promotion = promotion; }
    public void setBought(int bought) { this.bought = bought; }
    public void setPopular(boolean popular) { this.popular = popular; }
    public void setImageurl(String imageurl) { this.imageurl = imageurl; }

    public int getProduct_id() { return product_id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getShort_info() { return short_info; }
    public float getPromotion() { return promotion; }
    public int getBought() { return bought; }
    public boolean isPopular() { return popular; }
    public String getImageurl() { return imageurl; }
}
