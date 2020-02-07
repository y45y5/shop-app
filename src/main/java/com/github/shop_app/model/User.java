package com.github.shop_app.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String username;
    private String salt;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String zipcode;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    public User(){

    }

    public User(String username, String salt, String password,
                String email, String firstName, String lastName,
                String country, String city, String street, String zipcode) {
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public User(int user_id, String username, String salt, String password,
                String email, String firstName, String lastName,
                String country, String city, String street, String zipcode) {
        this.user_id = user_id;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public void addToCart(Product product){ productList.add(product); }

    public int getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getStreet() { return street; }
    public String getZipcode() { return zipcode; }
    public String getSalt() { return salt; }
    public List<Product> getProductList() { return productList; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setStreet(String street) { this.street = street; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }
    public void setSalt(String salt) { this.salt = salt; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
}
