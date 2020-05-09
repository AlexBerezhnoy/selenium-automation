package io.react.realworld;

import com.hillel.auto.User;
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

//            WebElement signUpButton = driver.findElement(By.cssSelector("#main > div > nav > div > ul > li:nth-child(3) > a"));
//            WebElement signUpButton = driver.findElement(By.linkText("Sign up"));

//            String currentURL = driver.getCurrentUrl();
//            assertThat(currentURL).contains("register");
        clickRegistrationButton();

        checkPage("Sign Up");
        assertThat(singForm().isDisplayed()).isTrue();
        User user = UserData.randomUser();

        inputText(userNameFiled(), user.getUserName());
        inputText(emailField(), user.getEmail());
        inputText(passwordField(), user.getPassword());

        clickSingInButton();

        userShouldBeLoggedIn(user.getUserName());

    }

    @Test
    public void validationRegistrationForm() {
        clickRegistrationButton();

       checkPage("Sign Up");

        clickSingInButton();

        List<WebElement> errorMessage = driver.findElements(By.cssSelector(".error-messages>li"));

//           assertThat(errorMessage).hasSize(3);
//           assertThat(errorMessage.get(0).getText()).isEqualTo("email can't be blank");
//           List<String> errors = new ArrayList<>();
//           for (WebElement error: errorMessage) {
//               errors.add(error.getText());
//           }
        List<String> errors = errorMessage.stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat(errorMessage).hasSize(3);
        assertThat(errors).contains("email can't be blank");
    }


        private void clickRegistrationButton() {
        WebElement signUpButton = driver.findElement(By.cssSelector("a[href='#register']"));
        signUpButton.click();
    }

    private WebElement userNameFiled() {
        return singForm().findElement(By.cssSelector("input[type='text']"));
    }

   }
