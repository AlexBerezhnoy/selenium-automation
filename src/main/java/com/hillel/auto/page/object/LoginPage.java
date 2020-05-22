package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private By emailInputField = By.cssSelector(".auth-page form input[type='email']");
    private By passwordInputField = By.cssSelector(".auth-page form input[type='password']");
    private By signInBtn = By.cssSelector(".auth-page form button[type='submit']");
    private By pageTitle = By.cssSelector(".auth-page h1");


    public LoginPage (WebDriver driver) {
        super(driver);
    }


    public HomePage login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        return clickSingInButton();
    }

    public void inputEmail(String email) {
        WebElement emailField = driver.findElement(emailInputField);
        inputText(emailField, email);
    }

    public void inputPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInputField);
        inputText(passwordField, password);
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public HomePage clickSingInButton() {
        WebElement signInButton = driver.findElement(signInBtn);
        signInButton.click();
        return new HomePage(driver);
    }

    public String getError () {
        return driver.findElement(By.cssSelector(".error-messages>li")).getText();
    }

}