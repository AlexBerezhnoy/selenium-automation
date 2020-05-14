package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends BasePage {
    private By editPostBtn = By.cssSelector(".article-meta>span>a");
    private By deleteBtn = By.cssSelector(".article-meta button.btn");
    private By title = By.cssSelector(".article-page");
    private By postText = By.cssSelector(".article-content p");

    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen () {
        return driver.findElement(title).isDisplayed();
    }

    public NewPostPage clickEditButton () {
        driver.findElement(editPostBtn).click();
        return new NewPostPage(driver);
    }

    public HomePage clickDeleteButton () {
        driver.findElement(deleteBtn).click();
        return new HomePage(driver);
    }
    public String getPostText () {
        return driver.findElement(postText).getText();
    }
}
