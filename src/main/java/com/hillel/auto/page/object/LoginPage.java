package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public HomePage login (String email, String password) {
        inputEmail(email);
        inputPassword(password);
        return new HomePage(driver);
    }

    public LoginPage(WebDriver driver) {
       super(driver);
    }

    public String getPageTitle () {
        return driver.findElement(By.cssSelector(".auth-page h1")).getText();
    }

    public void inputEmail (String email) {
        WebElement emailField = singForm().findElement(By.cssSelector("input[type='email']"));
    }

    public HomePage clickLoginButton (){
        WebElement signIpButton = driver.findElement(By.cssSelector("a[href = '#login']"));
        signIpButton.click();
        return new HomePage(driver);
    }

    public void inputPassword (String email) {
        WebElement emailField = singForm().findElement(By.cssSelector("input[type='password']"));
    }

    protected WebElement singForm() {
        return driver.findElement(By.cssSelector(".auth-page form"));
    }



}
