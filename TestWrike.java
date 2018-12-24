package com.gmail.waylacteal;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ElementNotVisibleException;

public class TestWrike {

    private static WebDriver driver;
    //генератор рандомной строки
    public static String RandomString(int StringSize){
        StringBuilder builder = new StringBuilder();
        String RandomStringElement = "qwertyuiopasdfghjklzxcvbnm0123456789";
        while (StringSize-- != 0) {
            int character = (int)(Math.random()*RandomStringElement.length());
            builder.append(RandomStringElement.charAt(character));
        }
        return builder.toString();
    }
    //генератор случайного ответа
    public static String[] randomButtonsName() {
        Random random = new Random();
        ArrayList<String[]> randomAnsList = new ArrayList<String[]>();
        String[] answer1Question = new String[]{"'Very interested'", "'Just looking'"};
        String[] answer2Question = new String[]{"'6–15'", "'1–5'", "'16–25'", "'26–50'", "'50+'"};
        String[] answer3Question = new String[]{"'Yes'", "'No'", "'Other'"};
        randomAnsList.add(answer1Question);
        randomAnsList.add(answer2Question);
        randomAnsList.add(answer3Question);
        int[] numberOfAnswer = new int[]{answer1Question.length, answer2Question.length, answer3Question.length};
        String[] randomAns = new String[3];
        for (int numAns = 0; numAns < numberOfAnswer.length; numAns++) {
            String[] answer = randomAnsList.get(numAns);
            int index = random.nextInt(numberOfAnswer[numAns]);
            randomAns[numAns] = answer[index];
        }
        return randomAns;
    }
    //ожидание с исключением
    public void sleep(int ms){
        try {
        Thread.sleep(ms);
    } catch (InterruptedException ie) {
    } }
    //проверка на кликабельность с исключением
    public void CheckOnClicable(WebElement element){
        try {
            sleep(5000);
            element.click();
            Assert.assertTrue(false);
        }catch (ElementNotVisibleException nn) {
            Assert.assertTrue(true);
        }
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wrike.com/");
    }

    @Test
    public void userLogin() {
        //создание аккаунта со случайным email и переход на следующую страницу
        String randomEmail = RandomString(6);
        driver.findElement(By.xpath("//div[@class='r']//button[@type='submit']")).click();
        WebElement BuisnessEmail = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']//input[@placeholder='Enter your business email']"));
        BuisnessEmail.sendKeys(randomEmail  + "wpt@wriketask.qaa");
        driver.findElement(By.xpath("//button[contains(text(),'Create my Wrike account')]")).click();
        //проверка перехода на новую страницу
        sleep(3000);
        Assert.assertEquals("https://www.wrike.com/resend/", driver.getCurrentUrl());
        //закрытие всплывающего фрейма
        sleep(6000);
        driver.switchTo().frame( driver.findElement(By.cssSelector("div > iframe")));
        driver.findElement(By.cssSelector(".Pv7Rnc")).click();
        driver.switchTo().defaultContent();
        //Resend email
        WebElement resendEmailButton = driver.findElement(By.xpath(" //button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']"));
        resendEmailButton.click();
        //проверка на удачную отправку формы
        sleep(3000);
        Assert.assertFalse(resendEmailButton.isDisplayed());
        //Случайный выбор ответов в меню слева и их отправка
        String[] randomAns = randomButtonsName();
        for (int i = 0; i < randomAns.length; i++) {
            WebElement button = driver.findElement(By.xpath("//button[contains(text(),"+randomAns[i]+")]"));
            button.click();
            if (randomAns[i] == "'Other'" ){
                WebElement butt = driver.findElement(By.xpath("//input[@placeholder='Your comment']"));
                butt.sendKeys("other");
            }
        }
        WebElement SubmitButton = driver.findElement(By.xpath("//form[@name='survey-form']//button[@type='submit']"));
        SubmitButton.click();
        //проверка на удачную отправку формы
        CheckOnClicable(SubmitButton);
        //проверка на удачную ссылку на twitter
        WebElement twitterButton = driver.findElement(By.xpath("//a[contains(@href, 'twitter')]"));
        String new_window_url = twitterButton.getAttribute("href");
        //проверка url
        driver.get(new_window_url);
        sleep(3000);
        Assert.assertEquals("https://twitter.com/wrike", driver.getCurrentUrl());
        driver.switchTo().parentFrame();
        }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}



