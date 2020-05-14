package io.react.realworld;

import com.hillel.auto.page.object.LoginPage;
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

public class FieldsSingInValidationTest {
    public WebDriver driver;

    String email = "testuser"+new Random().nextInt(10000)+"@rambler.ru";
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
        WebElement signIpButton = driver.findElement(By.cssSelector("a[href = '#login']"));
        signIpButton.click();

    }

    @AfterMethod
    public void quot() {
        driver.quit();
    }

    @Test
    public void emailBlankValidationTest () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(" ");
        loginPage.inputPassword(password);
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }
    @Test
    public void emailWrongValidationTest () {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(" "+email);
        loginPage.inputPassword(password);
        loginPage.clickSingInButton();
        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }

    @Test
    public void passwordWrongValidationTest () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(email);
        loginPage.inputPassword(" "+password);
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");

    }
    @Test
    public void passwordBlankValidationTest () {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputEmail(email);
        loginPage.inputPassword(" ");
        loginPage.clickSingInButton();

        WebElement errorText = driver.findElement(By.cssSelector(".error-messages>li"));
        assertThat(errorText.getText()).isEqualTo("email or password is invalid");
    }
}
