package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.page.object.ProfilePage;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserNavigationTest extends TestBase  {
    private User user = UserData.defaultUser();

    @Test
    public void NewPostPageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        homePage.clickNewPost();

        WebElement newPostPage = driver.findElement(By.cssSelector(".editor-page form"));
        assertThat(newPostPage.isDisplayed()).isTrue();
    }

    @Test
    public void UserHomePageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        WebElement yourFeedButton = driver.findElement(By.cssSelector(".feed-toggle li:nth-child(1) > a"));
        assertThat(yourFeedButton.isDisplayed()).isTrue();
        WebElement globalFeedButton = driver.findElement(By.cssSelector(".feed-toggle li:nth-child(2) > a"));
        assertThat(globalFeedButton.isDisplayed()).isTrue();
    }

    @Test
    public void SettingsPageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        ProfilePage profilePage = homePage.clickProfile();
        profilePage.clickSettingButton();

        WebElement settingsForm = driver.findElement(By.cssSelector(".settings-page form"));
        assertThat(settingsForm.isDisplayed()).isTrue();
    }
    @Test
    public void UserPageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        homePage.clickProfile();

        WebElement userPage = driver.findElement(By.cssSelector(".user-info"));
        assertThat(userPage.isDisplayed()).isTrue();
    }
}
