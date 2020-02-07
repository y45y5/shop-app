package com.github.shop_app.utility;

import com.github.shop_app.model.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static String usernameValidation(String username, List<User> userList){
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        Matcher matcher = pattern.matcher(username);
        if(username.length() >= 6){
            if(username.length() < 14) {
                if (matcher.matches()) {
                    for (User user : userList)
                        if (user.getUsername().equals(username)) return "Username already taken!";
                    return null;
                } else return "Username is incorrect! (No special characters permitted)";
            } else return "Username is too long! (Max. 14 characters)";
        } else return "Username is too short! (Min. 6 characters)";
    }

    public static String passwordValidation(String password, String confirmPassword){
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        Matcher matcher = pattern.matcher(password);
        if(password.length() > 6){
            if (matcher.matches()){
                if(password.equals(confirmPassword)) return null;
                else return "Passwords don't match!";
            } else return "Password is incorrect! (No special characters permitted)";
        } else return "Password is too short! (Min. 6 characters)";
    }

    public static String emailValidation(String emailAddress, List<User> userList){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher matcher = pattern.matcher(emailAddress);
        if(matcher.matches()){
            for (User user : userList) {
                if(emailAddress.equals(user.getEmail())){
                    return "Email is already in use!";
                }
            }
            return null;
        } else return "Email address is incorrect!";
    }

    public static String nameValidation(String firstName, String lastName){
        if(firstName.length() > 0){
            if(lastName.length() > 0) return null;
            else return "Last name can't be empty!";
        } else return "First name can't be empty!";
    }

    public static String countryCityValidation(String country, String city){
        if(country.length() > 0){
            if(city.length() > 0) return null;
            else return "City can't be empty!";
        } else return "Country can't be empty!";
    }

    public static String countryValidation(String country){
        if(country.length() > 0) return null;
        else return "Country can't be empty!";
    }

    public static String cityValidation(String city){
        if(city.length() > 0) return null;
        else return "City can't be empty!";
    }

    public static String streetValidation(String street){
        if (street.length() > 0) return null;
        else return "Street can't be empty!";
    }

    public static String zipcodeValidation(String zipcode){
        Pattern pattern = Pattern.compile("[0-9\\-]{4,}");
        Matcher matcher = pattern.matcher(zipcode);
        if(zipcode.length() > 0){
            if(matcher.matches()){ return null;}
            else return "Zip-code is incorrect!";
        } else return "Zip-code can't be empty!";
    }
}
