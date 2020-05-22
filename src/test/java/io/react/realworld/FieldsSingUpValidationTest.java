package io.react.realworld;

import com.hillel.auto.page.object.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldsSingUpValidationTest extends TestBase {
//    public WebDriver driver;
    String user = "TestUser"+new Random().nextInt(10000);
    String email = user+"@rambler.ru".toLowerCase();
    String password = "qwerty123";

    @Test
    public void userNameBlankValidationTest () {
        clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(" ");
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();

        String errorText = registrationPage.getError();
        assertThat(errorText).isEqualTo("username can't be blankis too short (minimum is 1 character)is too long (maximum is 20 characters)");
    }

    @Test
    public void emailBlankValidationTest () {
        clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(" ");
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();
        String errorText = registrationPage.getError();
        assertThat(errorText).isEqualTo("email can't be blank");

    }
    @Test
    public void emailValidationTest () {
        clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail("wqweq@v");
        registrationPage.inputPassword(password);
        registrationPage.clickSingInButton();
        String errorText = registrationPage.getError();
        assertThat(errorText).isEqualTo("email is invalid");

    }

    @Test
    public void passwordShortValidationTest () {
        clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword("1");
        registrationPage.clickSingInButton();
        String errorText = registrationPage.getError();
        assertThat(errorText).isEqualTo("password is too short (minimum is 8 characters)");

    }
    @Test
    public void passwordBlankValidationTest () {
        clickRegistrationButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.inputUser(user);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(" ");
        registrationPage.clickSingInButton();
        String errorText = registrationPage.getError();
        assertThat(errorText).isEqualTo("password can't be blank");
    }
}
