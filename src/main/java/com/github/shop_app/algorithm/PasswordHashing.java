package com.github.shop_app.algorithm;

import java.security.MessageDigest;
import java.util.Random;

public class PasswordHashing {
    public static String checkPassword(String password, String saltString){
        byte[] salt = saltString.getBytes();
        return getSecurePassword(password, salt);
    }

    public static String[] hashPassword(String password){
        String[] passAndSalt = new String[2];
        passAndSalt[0] = getRandomSaltString();
        byte[] salt = getSalt(passAndSalt[0]);
        passAndSalt[1] = getSecurePassword(password, salt);
        return passAndSalt;
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt(String saltString) {
        try{
            Random random = new Random();
            byte[] salt = saltString.getBytes();
            return salt;
        }
        catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    private static String getRandomSaltString(){
        String saltString = "";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 16; i++) saltString = stringBuilder.append(random.nextInt(10)).toString();
        return saltString;
    }
}
