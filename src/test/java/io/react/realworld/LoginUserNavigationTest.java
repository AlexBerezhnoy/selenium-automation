package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.*;
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
        NewPostPage newPostPage = homePage.clickNewPost();

        assertThat(newPostPage.newPostPageIsDisplayed()).isTrue();
    }

    @Test
    public void UserHomePageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());

        assertThat(homePage.yourFeedButton().isDisplayed()).isTrue();
        assertThat(homePage.globalFeedButton().isDisplayed()).isTrue();
    }

    @Test
    public void SettingsPageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        ProfilePage profilePage = homePage.clickProfile();
        SettingPage settingPage = profilePage.clickSettingButton();
        assertThat(settingPage.settingPageIsDisplayed()).isTrue();
    }
    @Test
    public void UserPageTest () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        ProfilePage profilePage = homePage.clickProfile();
        assertThat(profilePage.profilePageIsDisplayed()).isTrue();
    }
}
