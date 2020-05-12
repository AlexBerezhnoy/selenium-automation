package io.react.realworld;

import com.hillel.auto.User;
import com.hillel.auto.page.object.HomePage;
import com.hillel.auto.page.object.LoginPage;
import com.hillel.auto.utils.UserData;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends TestBase{
    private User user = UserData.defaultUser();
    @Test
    public void loginTest () {
         clickLoginButton();
         LoginPage loginPage = new LoginPage(driver);
        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

         loginPage.inputEmail(user.getEmail());
         loginPage.inputPassword(user.getPassword());

         HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
         assertThat(homePage.isUserLoggedIn(user.getUserName());
       }

    }
