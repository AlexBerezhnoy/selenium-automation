package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewArticlePage extends BasePage {

    private By titleField = By.cssSelector("");

    public NewArticlePage(WebDriver driver) {
        super(driver);
    }

    public void inputArticleTitle (String title) {
        inputText(driver.findElement(titleField), title);
    }


}
