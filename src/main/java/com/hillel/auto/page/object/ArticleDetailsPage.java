package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticleDetailsPage extends BasePage {

    private By title = By.cssSelector()
    public ArticleDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpen () {
        return driver.findElement(title).isDisplayed();
    }
}
