package com.gmail.waylacteal;

import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Random;

public class Help {
    //public WebDriver driver;
    public static void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
        } }

    //генератор случайной строки
    public static String randomString(int stringSize) {
        StringBuilder builder = new StringBuilder();
        String RandomStringElement = "qwertyuiopasdfghjklzxcvbnm0123456789";
        while (stringSize-- != 0) {
            int character = (int) (Math.random() * RandomStringElement.length());
            builder.append(RandomStringElement.charAt(character));
        }
        return builder.toString();
    }
    //генератор случайного email
    public static String randomEmail(int StringSize){
        return new String (randomString(StringSize)+"@"+randomString(5)+"."+randomString(2));
    }
    //генератор случайного ответа(на вход принимает ArrayList<String[]> из всевозможных ответов)
    public static String[] randomAns(ArrayList<String[]> inputAns) {
        int inputSize = inputAns.size();
        Random random = new Random();
        String[] randomAns = new String[inputSize];
        for (int j = 0; j < inputSize; j++){
            int numberOfAns = inputAns.get(j).length;
            int index = random.nextInt(numberOfAns);
            randomAns[j] = inputAns.get(j)[index];
        }
        return randomAns;
    }
    public void checkOnClicable(WebElement element){
        try {
            sleep(5000);
            element.click();
            Assert.assertTrue(false);
        }catch (ElementNotVisibleException nn) {
            Assert.assertTrue(true);
        }
    }
    public static boolean webElementPresent(WebDriver driver, By by) {
        sleep(3000);
        try {
            driver.findElement(by);
            return true;  // Success!
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }
}
