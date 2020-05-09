package io.react.realworld.newpost;

import com.hillel.auto.User;
import com.hillel.auto.utils.UserData;
import io.react.realworld.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewPost extends TestBase {
    private User user = UserData.defaultUser();
    protected void login() {
        clickLoginButton();
        inputText(emailField(), user.getEmail());
        inputText(passwordField(), user.getPassword());
        clickSingInButton();
    }

    protected void clickNewPostButton (){
        WebElement signIpButton = driver.findElement(By.cssSelector("a[href = '#editor']"));
        signIpButton.click();
    }
    protected WebElement postForm () {return driver.findElement(By.cssSelector("form"));}

    protected WebElement articleTitleField() {
        return postForm().findElement(By.cssSelector("fieldset:nth-child(1) > input"));
    }

    protected WebElement aboutArticleField() {
        return postForm().findElement(By.cssSelector("fieldset:nth-child(2) > input"));
    }

    protected WebElement generalPostField() {
        return postForm().findElement(By.cssSelector("fieldset:nth-child(3) > textarea"));
    }

    protected WebElement tagsField() {
        return postForm().findElement(By.cssSelector("fieldset:nth-child(4) > input"));
    }

    protected void clickPublishArticleButton() {
        WebElement publishArticleButton = driver.findElement(By.cssSelector("form button"));
        publishArticleButton.click();
    }
}
