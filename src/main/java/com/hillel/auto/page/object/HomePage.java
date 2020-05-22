package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private By profileBtn = By.xpath("//*[@class='user-pic']/..");
    private By newPostBtn = By.cssSelector("[href='#editor']");
    private By yourFeedBtn = By.cssSelector(".feed-toggle li:nth-child(1) > a");
    private By globalFeedBtn = By.cssSelector(".feed-toggle li:nth-child(2) > a");

    public HomePage (WebDriver driver) {
        super(driver);
    }

    public Boolean isUserLoggedIn(String userName) {
        WebElement userInfo = driver.findElement(By.cssSelector("[href='#@" +userName+"']"));
        return userInfo.isDisplayed();
    }

    public NewPostPage clickNewPost() {
        driver.findElement(newPostBtn).click();
        return new NewPostPage(driver);
    }

    public ProfilePage clickProfile () {
        driver.findElement(profileBtn).click();
        return new ProfilePage(driver);
    }

    public WebElement yourFeedButton () {
        return driver.findElement(yourFeedBtn);
    }

    public WebElement globalFeedButton () {
        return driver.findElement(globalFeedBtn);
    }
}
