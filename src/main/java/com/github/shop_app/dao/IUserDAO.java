package com.github.shop_app.dao;

import com.github.shop_app.model.User;

import java.util.List;

public interface IUserDAO {
    void createUser();
    void deleteUser();
    List<User> getUserList();
    void updateUser();
    void mergeUser();
}
