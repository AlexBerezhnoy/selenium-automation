package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRegistrationTest extends TestBase {


    @Test
    public void registrationTest() {

        clickRegistrationButton();

        checkPage("Sign Up");
        assertThat(singForm().isDisplayed()).isTrue();
        User user = UserData.randomUser();

        inputText(userNameFiled(), user.getUsername());
        inputText(emailField(), user.getEmail());
        inputText(passwordField(), user.getPassword());

        clickSingInButton();

        userShouldBeLoggedIn(user.getUsername());

    }

    @Test
    public void validationRegistrationForm() {
        clickRegistrationButton();

        checkPage("Sign Up");

        clickSingInButton();

        List<WebElement> errorMessage = driver.findElements(By.cssSelector(".error-messages>li"));

        List<String> errors = errorMessage.stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat(errorMessage).hasSize(3);
        assertThat(errors).contains("email can't be blank");
    }

    private WebElement userNameFiled() {
        return singForm().findElement(By.cssSelector("input[type='text']"));
    }

   }
