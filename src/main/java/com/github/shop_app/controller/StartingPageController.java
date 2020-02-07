package com.github.shop_app.controller;

import com.github.shop_app.algorithm.PasswordHashing;
import com.github.shop_app.model.User;
import com.github.shop_app.dao.UserDAO;
import com.github.shop_app.utility.DataValidation;
import com.github.shop_app.utility.GetList;
import com.github.shop_app.view.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class StartingPageController {

    @FXML private TextField usernameLogin;
    @FXML private PasswordField passwordLogin;

    @FXML private TextField usernameRegister;
    @FXML private PasswordField passwordRegister;
    @FXML private PasswordField confirmPasswordRegister;
    @FXML private TextField emailRegister;
    @FXML private TextField firstNameRegister;
    @FXML private TextField lastNameRegister;
    @FXML private TextField countryRegister;
    @FXML private TextField cityRegister;
    @FXML private TextField streetRegister;
    @FXML private TextField zipcodeRegister;

    @FXML private Label usernameError;
    @FXML private Label passwordError;
    @FXML private Label emailError;
    @FXML private Label nameError;
    @FXML private Label countryCityError;
    @FXML private Label streetError;
    @FXML private Label zipcodeError;
    @FXML private Label loginError;

    private List<User> userList = GetList.fetchUserList();

    @FXML void initialize(){
        Platform.runLater(this::resetErrors);
    }

    private void resetErrors(){
        usernameError.setText("");
        passwordError.setText("");
        emailError.setText("");
        nameError.setText("");
        countryCityError.setText("");
        streetError.setText("");
        zipcodeError.setText("");
        loginError.setText("");
    }

    @FXML private void onRegisterAction(){
        List<User> userList = GetList.fetchUserList();
        resetErrors();
        int validationLevel = 0;

        String username = usernameRegister.getCharacters().toString();
        String password = passwordRegister.getCharacters().toString();
        String confirmPassword = confirmPasswordRegister.getCharacters().toString();
        String emailAddress = emailRegister.getCharacters().toString();
        String firstName = firstNameRegister.getCharacters().toString();
        String lastName = lastNameRegister.getCharacters().toString();
        String country = countryRegister.getCharacters().toString();
        String city = cityRegister.getCharacters().toString();
        String street = streetRegister.getCharacters().toString();
        String zipcode = zipcodeRegister.getCharacters().toString();

        String validUsername = DataValidation.usernameValidation(username, userList);
        if(validUsername == null){ validationLevel++; }
        else usernameError.setText(validUsername);

        String validPassword = DataValidation.passwordValidation(password, confirmPassword);
        if(validPassword == null){ validationLevel++; }
        else passwordError.setText(validPassword);

        String validEmail = DataValidation.emailValidation(emailAddress, userList);
        if(validEmail == null){ validationLevel++; }
        else emailError.setText(validEmail);

        String validName = DataValidation.nameValidation(firstName, lastName);
        if(validName == null){ validationLevel++; }
        else nameError.setText(validName);

        String validCountryCity = DataValidation.countryCityValidation(country, city);
        if(validCountryCity == null){ validationLevel++; }
        else countryCityError.setText(validCountryCity);

        String validStreet = DataValidation.streetValidation(street);
        if(validStreet == null){ validationLevel++; }
        else streetError.setText(validStreet);

        String validZipcode = DataValidation.zipcodeValidation(zipcode);
        if(validZipcode == null){ validationLevel++; }
        else zipcodeError.setText(validZipcode);

        if(validationLevel == 7){
            String[] getPassword = PasswordHashing.hashPassword(password);

            User user = new User(
                    username, getPassword[0], getPassword[1], emailAddress,
                    firstName, lastName, country, city, street, zipcode);
            UserDAO userDAO = new UserDAO(user);
            userDAO.createUser();
            SceneManager.setActiveUser(user);
            SceneManager.setHomeScene();
        }
    }

    @FXML private void onLoginAction(){
        resetErrors();
        for (User user : userList) {
            if( usernameLogin.getCharacters().toString().equals(user.getUsername())){
                String hashedPass = PasswordHashing.checkPassword(passwordLogin.getCharacters().toString(), user.getSalt());
                if(hashedPass.equals(user.getPassword())){
                    SceneManager.setActiveUser(user);
                    SceneManager.setHomeScene();
                }
            }
        }
        loginError.setText("Username or password is incorrect!");
    }
}
