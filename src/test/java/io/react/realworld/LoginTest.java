package io.react.realworld;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    public WebDriver driver;

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    public void quot() {
        driver.quit();
    }

    @Test
    public void loginTest () {

         driver.get("https://react-redux.realworld.io");
         WebElement signIpButton = driver.findElement(By.cssSelector("a[href = '#login']"));
         signIpButton.click();
         WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
         assertThat(signInForm.isDisplayed()).isTrue();
         String user = "UserAleks1";
         String email = "useraleks1@gmail.com";
         String password = "qwerty123";
         WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
         emailField.clear();
         emailField.sendKeys(email);
         WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
         passwordField.clear();
         passwordField.sendKeys(password);
         WebElement signInButton = signInForm.findElement(By.cssSelector("button[type='submit']"));
         signInButton.click();
         WebElement userInfo = driver.findElement(By.cssSelector("[href='#@"+user+"']"));
         assertThat(userInfo.isDisplayed()).isTrue();
       }

}
