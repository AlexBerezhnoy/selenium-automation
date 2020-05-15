package io.react.realworld;

import com.hillel.auto.page.object.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldsSingInValidationTest extends TestBase{


    String email = "testuser"+new Random().nextInt(10000)+"@rambler.ru";
    String password = "qwerty123";

    @Test
    public void emailBlankValidationTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(" ");
        loginPage.inputPassword(password);
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }
    @Test
    public void emailWrongValidationTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(" "+email);
        loginPage.inputPassword(password);
        loginPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }

    @Test
    public void passwordWrongValidationTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(email);
        loginPage.inputPassword(" "+password);
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }
    @Test
    public void passwordBlankValidationTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(email);
        loginPage.inputPassword(" ");
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");
    }
}
