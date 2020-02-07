package com.github.shop_app.model;

public class ActiveUser{
    public static User activeUser;

    public static void setActiveUser(User user){
        activeUser = user;
    }

    public static User getUser(){
        return activeUser;
    }

    public static String getActiveUsername(){
        return activeUser.getUsername();
    }
}
