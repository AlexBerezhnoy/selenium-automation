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

public class UserRegistrationTest {
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
            public void registrationTest() {
           driver.get("https://react-redux.realworld.io");
//            WebElement signUpButton = driver.findElement(By.cssSelector("#main > div > nav > div > ul > li:nth-child(3) > a"));
//            WebElement signUpButton = driver.findElement(By.linkText("Sign up"));
           WebElement signUpButton = driver.findElement(By.cssSelector("a[href = '#register']"));
           signUpButton.click();
//            String currentURL = driver.getCurrentUrl();
//            assertThat(currentURL).contains("register");
           WebElement signUpHeader = driver.findElement(By.cssSelector(".auth-page h1"));
           assertThat(signUpHeader.getText()).isEqualTo("Sign Up");

           WebElement signUpForm = driver.findElement(By.cssSelector(".auth-page form"));
           assertThat(signUpForm.isDisplayed()).isTrue();

           String user = "TestUser"+new Random().nextInt(10000);
           String email = user+"@rambler.ru";
           String password = "qwerty123";

           WebElement userNameField = signUpForm.findElement(By.cssSelector("input[type='text']"));
           userNameField.clear();
           userNameField.sendKeys(user);

           WebElement emailField = signUpForm.findElement(By.cssSelector("input[type='email']"));
           emailField.clear();
           emailField.sendKeys(email);

           WebElement passwordField = signUpForm.findElement(By.cssSelector("input[type='password']"));
           passwordField.clear();
           passwordField.sendKeys(password);

           WebElement signInButton = signUpForm.findElement(By.cssSelector("button[type='submit']"));
           signInButton.click();

           WebElement userInfo = driver.findElement(By.cssSelector("[href='#@"+user+"']"));
           assertThat(userInfo.isDisplayed()).isTrue();

       }

//       @Test
//    public void loginTest () {
//            String user = "TestUser";
//            String email = "UserTest@rambler.ru";
//           String password = "qwerty123";
//       }

}
