package com.gmail.waylacteal;

import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.lang.Object;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.interactions.Actions;
import com.thoughtworks.selenium.webdriven.commands.GetEval;
//@RunWith(DataProviderRunner.class)


public class TestWrike {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.wrike.com/");
    }

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
        myArrayList2.add("'6-15'");
        myArrayList2.add("'1-5'");
        myArrayList2.add("'16-25'");
        myArrayList2.add("'26-50'");
        myArrayList2.add("'50+'");

        ArrayList<String> myArrayList3 = new ArrayList<String>();
        myArrayList3.add("'Yes'");
        myArrayList3.add("'No'");

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

    public void openInNewWindow(String url) {
        ((JavascriptExecutor) driver)
                .executeScript("window.open(arguments[0])", url);
    }

    @Test
    public void autotest() {
        String b = Random(6);
 //создание аккаунта со случайным email
        WebElement GetStartForFreeButton = driver.findElement(By.xpath("//div[@class='r']//button[@type='submit']"));
        GetStartForFreeButton.click();
        WebElement BuisnessEmail = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']//input[@placeholder='Enter your business email']"));
        BuisnessEmail.sendKeys(b + "wpt@wriketask.qaa");
        WebElement Create = driver.findElement(By.xpath("//button[contains(text(),'Create my Wrike account')]"));
        Create.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ie) {
        }
        String Page = driver.getCurrentUrl();
 //проверка на исключение
        Assert.assertEquals("https://www.wrike.com/resend/", Page);
//Resend email
        driver.findElement(By.xpath(" //button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']")).click();
        boolean noexist = driver.findElements(By.xpath(" //button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']")).size()>0 ;
//проверяем с исключением
        Assert.assertFalse(noexist);
//случайный выбор ответов в поле слева
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
        WebElement button4 = driver.findElement(By.xpath("//form[@name='survey-form']//button[@type='submit']"));
        button4.click();
        //try {
        //    Thread.sleep(3000);
        //} catch (InterruptedException ie) {
        //}
        boolean noexist = driver.findElements(By.xpath("//form[@name='survey-form']//button[@type='submit']")).size()>0 ;
//проверка на исключение
        Assert.assertFalse(noexist);
//twitter
        WebElement svgObject = driver.findElement(By.xpath("//a[contains(@href, 'twitter')]"));
        String new_window_url = svgObject.getAttribute("href");
        driver.get(new_window_url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        }
        String Page2 = driver.getCurrentUrl();
//проверка на исключение
        Assert.assertEquals("https://twitter.com/wrike", Page2);
        //driver.switchTo().parentFrame();

   }

  /*@Test
    //public void CloseThrFFFFWindow(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions action = new Actions(driver);
        //WebElement we = driver.findElement(By.xpath("//div[@class='SxbKmc']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body")));
        WebElement we = driver.findElement(By.xpath("/html/body"));
        //action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html/body/div[6]")));
        //WebElement we2 = driver.findElement(By.xpath("/html/body/div[6]"));
        action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html/body/c-wiz/div/div/div[2]/div[4]/span[1]/div"))).click().build().perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("'//div[@class='ZFr60d.CeoRYc']")));
        //WebElement YESNO =driver.findElement(By.xpath("//span[contains( text(),'NO')]"));
        //driver.findElement(By.className("RveJvd.snByac"));
        //driver.findElement(By.xpath("//span[contains( text(),'NO')]")).click();
    //}*/
    
    
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}



