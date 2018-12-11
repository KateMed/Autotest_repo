package com.gmail.waylacteal;

import java.lang.Object;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotVisibleException;

public class TestWrike {
    private static WebDriver driver;
    public static String Random(int count){
        StringBuilder builder = new StringBuilder();
        String ahString = "qwertyuiopasdfghjklzxcvbnm0123456789";
        while (count-- != 0) {
            int character = (int)(Math.random()*ahString.length());
            builder.append(ahString.charAt(character));
        }
        String b = builder.toString();
        return b;
    }
    public static Object[] ButtonsName(){

        ArrayList<String> myArrayList1 = new ArrayList<String>();
        myArrayList1.add("'Very interested'");
        myArrayList1.add("'Just looking'");

        ArrayList<String> myArrayList2 = new ArrayList<String>();
        myArrayList2.add("'6–15'");
        myArrayList2.add("'1–5'");
        myArrayList2.add("'16–25'");
        myArrayList2.add("'26–50'");
        myArrayList2.add("'50+'");

        ArrayList<String> myArrayList3 = new ArrayList<String>();
        myArrayList3.add("'Yes'");
        myArrayList3.add("'No'");
        myArrayList3.add("'Other'");

        return new Object[]{myArrayList1, myArrayList2, myArrayList3};
    }

    public static String RandomButton(ArrayList myArrList){
        StringBuilder bt1 = new StringBuilder();
        int j = (int)(Math.random()*myArrList.size());
        bt1.append(myArrList.get(j));
        String bt1S = bt1.toString();
        return bt1S;
    }

    public static ArrayList<String> Calc(){
        Object[] myObj = ButtonsName();
        ArrayList<String> S = new ArrayList<String>();
        S.add(RandomButton((ArrayList) myObj[0]));
        S.add(RandomButton((ArrayList) myObj[1]));
        S.add(RandomButton((ArrayList) myObj[2]));
        return S;
    }

    public void sleep(int ms){
        try {
        Thread.sleep(ms);
    } catch (InterruptedException ie) {
    } }

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

//создание аккаунта со случайным email
        String b = Random(6);
        driver.findElement(By.xpath("//div[@class='r']//button[@type='submit']")).click();
        WebElement BuisnessEmail = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']//input[@placeholder='Enter your business email']"));
        BuisnessEmail.sendKeys(b + "wpt@wriketask.qaa");
        driver.findElement(By.xpath("//button[contains(text(),'Create my Wrike account')]")).click();
//проверка перехода на новую страницу
        sleep(3000);
        String Page = driver.getCurrentUrl();
        Assert.assertEquals("https://www.wrike.com/resend/", Page);
//закрытие фрейма
        sleep(6000);
        driver.switchTo().frame( driver.findElement(By.cssSelector("div > iframe")));
        driver.findElement(By.cssSelector(".Pv7Rnc")).click();
        driver.switchTo().defaultContent();
//Resend email
        WebElement but = driver.findElement(By.xpath(" //button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']"));
        but.click();
//проверка на удачную отправку формы
        sleep(3000);
        Assert.assertFalse(but.isDisplayed());
//Случайный выбор ответов в меню слева
        ArrayList<String> S = Calc();
        String S1 = S.get(0);
        String S2 = S.get(1);
        String S3 = S.get(2);
        WebElement button = driver.findElement(By.xpath("//button[contains(text(),"+S1+")]"));
        button.click();
        WebElement button2 = driver.findElement(By.xpath("//button[contains(text(),"+S2+")]"));
        button2.click();
        WebElement button3 = driver.findElement(By.xpath("//button[contains(text(),"+S3+")]"));
        button3.click();
        if (S3 == "'Other'" ){
            WebElement butt = driver.findElement(By.xpath("//input[@placeholder='Your comment']"));
            butt.sendKeys("other");
        }
        WebElement button4 = driver.findElement(By.xpath("//form[@name='survey-form']//button[@type='submit']"));
        button4.click();
//проверка на удачную отправку формы
        CheckOnClicable(button4);
//twitter
        WebElement svgObject = driver.findElement(By.xpath("//a[contains(@href, 'twitter')]"));
        String new_window_url = svgObject.getAttribute("href");
        driver.get(new_window_url);
        sleep(3000);
        String Page2 = driver.getCurrentUrl();
//проверка url
        Assert.assertEquals("https://twitter.com/wrike", Page2);
        driver.switchTo().parentFrame();
        }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}



