package io.react.realworld;

import com.hillel.auto.page.object.RegistrationPage;
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
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(" ");
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("username can't be blankis too short (minimum is 1 character)is too long (maximum is 20 characters)");
    }

    @Test
    public void emailBlankValidationTest () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(" ");
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email can't be blank");

    }
    @Test
    public void emailValidationTest () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail("wqweq@v");
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email is invalid");

    }

    @Test
    public void passwordShortValidationTest () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword("1");
        registrationPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("password is too short (minimum is 8 characters)");

    }
    @Test
    public void passwordBlankValidationTest () {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(" ");
        registrationPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("password can't be blank");
    }
}
