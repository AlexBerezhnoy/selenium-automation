package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage extends BasePage {

    private By emailInputField = By.cssSelector(".auth-page form input[type='email']");
    private By userInputField = By.cssSelector(".auth-page form input[type='email']");
    private By passwordInputField = By.cssSelector(".auth-page form input[type='password']");
    private By signInBtn = By.cssSelector(".auth-page form button[type='submit']");
    private By pageTitle = By.cssSelector(".auth-page h1");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }


    public void inputUser(String username) {
        WebElement userField = driver.findElement(userInputField);
        inputText(userField, username);
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

    public void clickSingInButton() {
        WebElement signInButton = driver.findElement(signInBtn);
        signInButton.click();
    }


}