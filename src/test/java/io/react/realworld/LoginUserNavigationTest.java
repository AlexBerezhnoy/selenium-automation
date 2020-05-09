package io.react.realworld;

import com.hillel.auto.User;
import com.hillel.auto.utils.UserData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserNavigationTest extends TestBase {
    public WebDriver driver;
    private User user = UserData.defaultUser();


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.get("https://react-redux.realworld.io");
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href = '#login']"));
        signUpButton.click();
        WebElement signInForm = driver.findElement(By.cssSelector(".auth-page form"));
        assertThat(signInForm.isDisplayed()).isTrue();

        WebElement emailField = signInForm.findElement(By.cssSelector("input[type='email']"));
        emailField.clear();
        emailField.sendKeys(user.getEmail());
        WebElement passwordField = signInForm.findElement(By.cssSelector("input[type='password']"));
        passwordField.clear();
        passwordField.sendKeys(user.getPassword());
        WebElement signInButton = signInForm.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();
    }

    @AfterClass
    public void quot() {
        driver.quit();
    }

    @Test
    public void NewPostPageTest () {
        WebElement newPostButton = driver.findElement(By.cssSelector("a[href = '#editor']"));
        newPostButton.click();
        WebElement newPostPage = driver.findElement(By.cssSelector(".editor-page form"));
        assertThat(newPostPage.isDisplayed()).isTrue();
    }

    @Test
    public void UserHomePageTest () {
        WebElement homePageButton = driver.findElement(By.cssSelector("a[href = '#']"));
        homePageButton.click();
        WebElement yourFeedButton = driver.findElement(By.cssSelector(".feed-toggle li:nth-child(1) > a"));
        assertThat(yourFeedButton.isDisplayed()).isTrue();
        WebElement globalFeedButton = driver.findElement(By.cssSelector(".feed-toggle li:nth-child(2) > a"));
        assertThat(globalFeedButton.isDisplayed()).isTrue();
    }

    @Test
    public void SettingsPageTest () {
        WebElement settingsButton = driver.findElement(By.cssSelector("a[href = '#settings']"));
        settingsButton.click();
        WebElement settingsForm = driver.findElement(By.cssSelector(".settings-page form"));
        assertThat(settingsForm.isDisplayed()).isTrue();
    }
    @Test
    public void UserPageTest () {
        WebElement userButton = driver.findElement(By.cssSelector("a[href = '#@"+user.getUserName()+"']"));
        userButton.click();
        WebElement userPage = driver.findElement(By.cssSelector(".user-info"));
        assertThat(userPage.isDisplayed()).isTrue();
    }
}
