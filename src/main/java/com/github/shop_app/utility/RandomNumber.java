package com.github.shop_app.utility;

import java.util.Random;

public class RandomNumber {
    private static Random random = new Random();

    public static int randomNumbers(int number1, int number2, int bound){
        if(number1 == number2) return randomNumbers(random.nextInt(bound), random.nextInt(bound), bound);
        else return number2;
    }
}
