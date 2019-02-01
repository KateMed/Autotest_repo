package com.gmail.waylacteal.pages;

import com.gmail.waylacteal.Help;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class ResendPage {
    public ResendPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        //???  this.driver = driver;
    }

    public static Help help;
    //public WebDriver driver;
    public String resendElementLocator = "//button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']";
    public By resendElemetBy = By.xpath(resendElementLocator);
    public By submitResBy = By.xpath("//form[@name='survey-form']//button[@type='submit']");
    public String urlResendPage = "https://www.wrike.com/resend/";

    @FindBy(css = "div > iframe")
    public static WebElement frameField;

    @FindBy(css = ".Pv7Rnc")
    WebElement closeFrameButton;

    @FindBy(xpath = "//button[@class='wg-btn wg-btn--white wg-btn--hollow button js-button']")
    public WebElement resendEmailButton;

    @FindBy(xpath = "//input[@placeholder='Your comment']")
    public WebElement commentField;

    @FindBy(xpath = "//form[@name='survey-form']//button[@type='submit']")
    public WebElement submitResultButton;

    @FindBy(xpath = "//a[contains(@href, 'twitter')]")
    public WebElement twitterButton;

    //вот эти кнопки логичнее искать иначе, но это уже в следующей серии
    //можно вывести все элементы по какому-нибудь локатору и выбрать из них
    public static ArrayList answersList(){
        ArrayList<String[]> ansList = new ArrayList<String[]>();
        ansList.add(new String[]{"'Very interested'","'Just looking'"});
        ansList.add(new String[]{"'6–15'", "'1–5'", "'16–25'", "'26–50'", "'50+'"});
        ansList.add(new String[]{"'Yes'", "'No'", "'Other'"});
        //System.out.print(ansList.get(0)[0]);
        return ansList;
    }

    public void closeFrame(WebDriver driver) {
        driver.switchTo().frame(frameField);
        closeFrameButton.click();
        driver.switchTo().defaultContent();
    }
    public void resendEmail() {
        resendEmailButton.click();
    }
    public void clickSubmtButton() {
        submitResultButton.click();
    }
    public void sendOtherComment(){
        commentField.sendKeys("other");
    }
    public void justLooking(WebDriver driver){driver.findElement(By.xpath("//button[contains(text(),'Just looking')]")).click();}
    public void randomAns(WebDriver driver){
        String[] randomAns = help.randomAns(answersList());
        for (int i = 0; i < randomAns.length; i++) {
            driver.findElement(By.xpath("//button[contains(text(),"+randomAns[i]+")]")).click();
            if (randomAns[i] == "'Other'" ){
                sendOtherComment();
            }
        }
    }
    public void clickTwitter(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(twitterButton).click().perform();
        //twitterButton.click();
        }

    public String urlTwitterPage(WebDriver driver){
        String new_window_url = twitterButton.getAttribute("href");
        driver.get(new_window_url);
        help.sleep(3000);
        String url = driver.getCurrentUrl();
        driver.switchTo().parentFrame();
        return url;
    }
}
