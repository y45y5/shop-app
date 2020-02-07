package com.github.shop_app.dao;

import com.github.shop_app.model.User;

import java.util.List;

public class UserDAO extends DAO<User> implements IUserDAO {

    private User user;

    public UserDAO(User user){ this.user = user; }
    public UserDAO() {   }

    @Override
    public void updateUser(){
        updateObject(user);
    }

    @Override
    public void createUser() {
        saveObject(user);
    }

    @Override
    public void deleteUser() {
        deleteObject(user);
    }

    @Override
    public List<User> getUserList() {
        return getObjectList(User.class);
    }

    @Override
    public void mergeUser() { mergeObject(user); }
}
