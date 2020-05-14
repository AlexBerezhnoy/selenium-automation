package com.hillel.auto.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewPostPage extends BasePage {

    private By titleField = By.cssSelector(".form-group:nth-child(1)>input");
    private By whatAboutField = By.cssSelector(".form-group:nth-child(2)>input");
    private By articleBodyField = By.cssSelector(".form-group:nth-child(3)>textarea");
    private By tagsField = By.cssSelector(".form-group:nth-child(4)>input");
    private By publishArticleBtn = By.cssSelector("[type='button']");

    public NewPostPage(WebDriver driver) {
        super(driver);
    }

    public void inputArticleTitle(String title) {
        inputText(titleField, title);
    }

    public void inputWhatArticleAbout(String whatAbout) {
        inputText(whatAboutField, whatAbout);
    }

    public void inputArticle(String article) {
        inputText(articleBodyField, article);
    }

    public void inputTags(String tags) {
        inputText(tagsField, tags);
    }

    public ArticleDetailsPage clickPublishArticleBtn() {
        driver.findElement(publishArticleBtn).click();
        return new ArticleDetailsPage(driver);
    }


}
