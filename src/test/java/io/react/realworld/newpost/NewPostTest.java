package io.react.realworld.newpost;

import com.hillel.auto.User;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class NewPostTest extends NewPost {
    private User user = UserData.defaultUser();
    String title = "New Test Post";
    String about = "For testing";
    String postText = "Post For testing";
    String tags = "Tags!";

    @Test(priority = 1 )
    public void createPost () {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL dd yyyy").withLocale(Locale.ENGLISH);;
        String nowDate = LocalDate.now().getDayOfWeek()+" "+ date.format(formatter);
        System.out.println(nowDate);
        login();
        clickNewPostButton ();
        inputText(articleTitleField(), title);
        inputText(aboutArticleField(), about);
        inputText(generalPostField(), postText);
        inputText(tagsField(), tags);
        clickPublishArticleButton();
        WebElement postTitle = driver.findElement(By.cssSelector("#main > div > div > div.banner > div > h1"));
        assertThat(postTitle.getText()).isEqualTo(title);
        String datePost = getDatePost().getText();
        assertThat(datePost).isEqualTo(nowDate);
//        WebDriverWait wait = new WebDriverWait(driver, 4);
//        wait.until();
    }

    @Test(priority = 2 )
    public void editPost () {
        login();
        clickUserButton(user.getUserName());
        clickMyArticles();
        clickLastPost();
        clickEditButton();
        postText = postText+" EDIT";
        inputText(articleTitleField(), title);
        inputText(aboutArticleField(), about);
        inputText(generalPostField(), postText);
        inputText(tagsField(), tags);
        clickPublishArticleButton();
        WebElement postTextDisplay = driver.findElement(By.cssSelector("#main > div > div > div.container.page > div.row.article-content > div > div > p"));
        assertThat(postTextDisplay.getText()).isEqualTo(postText);


    }

    @Test (priority = 3 )
    public void deletePost () {
        login();
        clickUserButton(user.getUserName());
        clickMyArticles();
        clickLastPost();
        clickDeleteButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement myArticlesPosts = driver.findElement(By.cssSelector("#main > div > div > div.container > div > div > div.article-preview"));
        assertThat(myArticlesPosts.getText()).isEqualTo("No articles are here... yet.");

    }


    public void clickMyArticles() {
        WebElement myPost = driver.findElement(By.cssSelector("#main > div > div > div.container > div > div > div.articles-toggle > ul > li:nth-child(1) > a"));
        myPost.click();
    }

    public void clickLastPost () {
        WebElement lastPost = driver.findElement(By.cssSelector("#main > div > div > div.container > div > div > div:nth-child(2) > div > a"));
        lastPost.click();
    }

    public void clickEditButton () {
        WebElement editButton = driver.findElement(By.cssSelector("#main > div > div > div.banner > div > div > span > a"));
        editButton.click();
    }

    public void clickDeleteButton () {
        WebElement deleteButton = driver.findElement(By.cssSelector("#main > div > div > div.banner > div > div > span > button"));
        deleteButton.click();
    }

    public WebElement getDatePost () {
        return driver.findElement(By.cssSelector("#main > div > div > div.banner > div > div > div > span"));
    }
}
