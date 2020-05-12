package com.hillel.auto.page.object;

import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePage extends BasePage {

    private By article = By.cssSelector("");
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public int getArticleSize (){
        return driver.findElements(article).size();
    }

}