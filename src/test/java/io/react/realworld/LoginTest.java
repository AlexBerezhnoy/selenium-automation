package io.react.realworld;

import com.hillel.auto.User;
import com.hillel.auto.utils.UserData;

import org.testng.annotations.Test;

public class LoginTest extends TestBase{
    private User user = UserData.defaultUser();
    @Test
    public void loginTest () {
         clickLoginButton();
         checkPage("Sign In");
         inputText(emailField(), user.getEmail());
         inputText(passwordField(), user.getPassword());
         clickSingInButton();
         userShouldBeLoggedIn(user.getUserName());
       }

    }
