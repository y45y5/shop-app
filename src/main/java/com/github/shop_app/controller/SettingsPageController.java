package com.github.shop_app.controller;

import com.github.shop_app.model.ActiveUser;
import com.github.shop_app.model.User;
import com.github.shop_app.dao.UserDAO;
import com.github.shop_app.utility.DataValidation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SettingsPageController extends MenuController implements IMenuController{

    @FXML private Label loadingScreen;
    @FXML private Label newEmailAddressError;
    @FXML private Label newCountryError;
    @FXML private Label newCityError;
    @FXML private Label newStreetError;
    @FXML private Label newZipcodeError;

    @FXML private TextField newEmailAddress;
    @FXML private TextField newCountry;
    @FXML private TextField newCity;
    @FXML private TextField newStreet;
    @FXML private TextField newZipcode;

    private List<User> userList = fetchUserList();

    @FXML void initialize(){
        Platform.runLater(() -> {
            resetErrors();
            fadeOut();
        });
    }

    private void resetErrors(){
        newEmailAddressError.setText("");
        newCountryError.setText("");
        newCityError.setText("");
        newStreetError.setText("");
        newZipcodeError.setText("");
    }

    @FXML private void onDeleteAccountAction(){
        UserDAO userDAO = new UserDAO(ActiveUser.getUser());
        userDAO.deleteUser();
        logOutAction();
    }

    @FXML private void onChangeEmailAction(){
        String emailAddress = newEmailAddress.getCharacters().toString();
        String validEmailAddress = DataValidation.emailValidation(emailAddress, userList);
        if(validEmailAddress == null){
            ActiveUser.getUser().setEmail(emailAddress);
            UserDAO userDAO = new UserDAO(ActiveUser.getUser());
            userDAO.mergeUser();
            newEmailAddressError.setText("Email address has been updated!");
            newEmailAddress.setDisable(true);
        }
        else newEmailAddressError.setText(validEmailAddress);
    }

    @FXML private void onChangeCountryAction(){
        String country = newCountry.getCharacters().toString();
        String validCountry = DataValidation.countryValidation(country);
        if(validCountry == null){
            ActiveUser.getUser().setCountry(country);
            UserDAO userDAO = new UserDAO(ActiveUser.getUser());
            userDAO.mergeUser();
            newCountryError.setText("Country has been updated!");
            newCountry.setDisable(true);
        }
        else newCountryError.setText(validCountry);
    }

    @FXML private void onChangeCityAction(){
        String city = newCity.getCharacters().toString();
        String validCity = DataValidation.cityValidation(city);
        if(validCity == null){
            ActiveUser.getUser().setCity(city);
            UserDAO userDAO = new UserDAO(ActiveUser.getUser());
            userDAO.mergeUser();
            newCityError.setText("City has been updated!");
            newCity.setDisable(true);
        }
        else newCityError.setText(validCity);
    }

    @FXML private void onChangeStreetAction(){
        String street = newStreet.getCharacters().toString();
        String validStreet = DataValidation.streetValidation(street);
        if(validStreet == null){
            ActiveUser.getUser().setStreet(street);
            UserDAO userDAO = new UserDAO(ActiveUser.getUser());
            userDAO.mergeUser();
            newStreetError.setText("Street has been updated!");
            newStreet.setDisable(true);
        }
        else newStreetError.setText(validStreet);
    }

    @FXML private void onChangeZipcodeAction(){
        String zipcode = newZipcode.getCharacters().toString();
        String validZipcode = DataValidation.zipcodeValidation(zipcode);
        if(validZipcode == null){
            ActiveUser.getUser().setZipcode(zipcode);
            UserDAO userDAO = new UserDAO(ActiveUser.getUser());
            userDAO.mergeUser();
            newZipcodeError.setText("Zip-code has been updated!");
            newZipcode.setDisable(true);
        }
        else newZipcodeError.setText(validZipcode);
    }

    private List<User> fetchUserList(){
        UserDAO userDAO = new UserDAO(new User());
        return userDAO.getUserList();
    }

    @Override
    public void fadeOut(){ fadeOutAction(loadingScreen); }

    @Override
    @FXML public void onLogOutAction() { logOutAction(); }

    @Override
    @FXML public void onSettingsAction() {  }

    @Override
    @FXML public void onHomeAction() { homeAction(); }

    @Override
    @FXML public void onSearchAction() { searchAction(); }

    @Override
    @FXML public void onYourCartAction() { yourCartAction(); }
}
