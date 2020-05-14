package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    private By myArticle = By.cssSelector(".articles-toggle  li:nth-child(1) > a");

    private By article = By.cssSelector(".article-preview");
    private By settingBtn = By.cssSelector("a[href = '#settings']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public int getArticleSize (){
        return driver.findElements(article).size();
    }

    public void clickMyArticles() {
        waits.visibilityOfElementLocated(myArticle).click();

    }

    public SettingPage clickSettingButton () {
        driver.findElement(settingBtn).click();
        return new SettingPage(driver);
    }

    public ArticleDetailsPage clickLastPost () {
        waits.visibilityOfElementLocated(article);
        driver.findElements(article).get(0).click();
        return new ArticleDetailsPage(driver);
    }

}