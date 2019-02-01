package com.gmail.waylacteal;

import com.gmail.waylacteal.pages.LoginPage;
import com.gmail.waylacteal.pages.ResendPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestWrikePO {
    public static WebDriver driver;
    //public static WebDriverWait wait;
    public static LoginPage loginPage;
    public static ResendPage resendPage;
    public static Help help;
    //нужно сделать все исключения человеческими

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver,10);
        loginPage = new LoginPage(driver);//передаем driver, тк этого требует инициализация pageFactory
        resendPage = new ResendPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.wrike.com/");
    }
    @Test
    public void test(){
        loginPage.clickGetStartedButton();
        loginPage.inputEmail(help.randomEmail(6));
        loginPage.clickCreateAccountButton();
        help.sleep(3000);
        Assert.assertEquals(resendPage.urlResendPage, driver.getCurrentUrl());
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //or
        //help.sleep(6000);
        //resendPage.closeFrame();
        resendPage.resendEmail();
        //help.sleep(3000);
        Assert.assertFalse(help.webElementPresent(driver, resendPage.resendElemetBy));
        //resendPage.justLooking(driver);
        resendPage.randomAns(driver);
        resendPage.clickSubmtButton();
        Assert.assertTrue(resendPage.submitResultButton.isEnabled());
        resendPage.clickTwitter(driver);
        String twitterUrl = resendPage.urlTwitterPage(driver);
        Assert.assertEquals("https://twitter.com/wrike", twitterUrl);
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
