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

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldsSingUpValidationTest {
    public WebDriver driver;
    String user = "TestUser"+new Random().nextInt(10000);
    String email = user+"@rambler.ru".toLowerCase();
    String password = "qwerty123";

    @BeforeClass
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.get("https://react-redux.realworld.io");
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href = '#register']"));
        signUpButton.click();

    }

    @AfterMethod
    public void quot() {
        driver.quit();
    }

    @Test
    public void userNameBlankValidationTest () {
        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(" ");

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("username can't be blank");
    }

    @Test
    public void emailBlankValidationTest () {

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(user);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(" ");

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email can't be blank");

    }
    @Test
    public void emailValidationTest () {

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(user);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys("wqweq@v");

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email is invalid");

    }

    @Test
    public void passwordShortValidationTest () {

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(user);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys("1");

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("password is too short (minimum is 8 characters)");

    }
    @Test
    public void passwordBlankValidationTest () {

        WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));

        WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
        userNameField.clear();
        userNameField.sendKeys(user);

        WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(" ");

        WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("password can't be blank");
    }
}
