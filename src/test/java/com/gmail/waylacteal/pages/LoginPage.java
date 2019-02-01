package com.gmail.waylacteal.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//div[@class='r']//button[@type='submit']")
    WebElement getStartedButton;

    @FindBy(xpath = "//label[@class='modal-form-trial__label']//input[@placeholder='Enter your business email']")
    WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Create my Wrike account')]")
    WebElement createAccountButton;

    /*public void getStartedForFree(String email) {
        getStartedButton.click();
        emailField.sendKeys(email);
        createAccountButton.click();
    }*/

    public void clickGetStartedButton() {
        getStartedButton.click();
    }
    @Step
    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }
    public void clickCreateAccountButton() {
        createAccountButton.click();
    }
}
