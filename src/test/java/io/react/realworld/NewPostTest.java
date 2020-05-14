package io.react.realworld;

import com.hillel.auto.model.User;
import com.hillel.auto.page.object.*;
import com.hillel.auto.utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class NewPostTest extends TestBase {
    private User user = UserData.defaultUser();
    String title = "New Test Post";
    String about = "For testing";
    String postText = "Post For testing";
    String tags = "Tags!";

    @Test(priority = 1 )
    public void createPost () {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE MMM dd yyyy").withLocale(Locale.ENGLISH);;
        String nowDate = date.format(formatter);
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        NewPostPage newPostPage = homePage.clickNewPost();
        newPostPage.inputArticleTitle(title);
        newPostPage.inputWhatArticleAbout(about);
        newPostPage.inputArticle(postText);
        newPostPage.inputTags(tags);
        ArticleDetailsPage articleDetailsPage = newPostPage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.getPostText()).isEqualTo(postText);
        String datePost = getDatePost().getText();
        assertThat(datePost).isEqualTo(nowDate);

    }

    @Test(priority = 2 )
    public void editPost () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        ProfilePage profilePage = homePage.clickProfile();
        profilePage.clickMyArticles();
        ArticleDetailsPage articleDetailsPage = profilePage.clickLastPost();
        NewPostPage newPostPage = articleDetailsPage.clickEditButton();

        postText = postText+" EDIT";
        newPostPage.inputArticleTitle(title);
        newPostPage.inputWhatArticleAbout(about);
        newPostPage.inputArticle(postText);
        newPostPage.inputTags(tags);
        articleDetailsPage = newPostPage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.getPostText()).isEqualTo(postText);


    }

    @Test (priority = 3 )
    public void deletePost () {
        clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.login(user.getEmail(), user.getPassword());
        ProfilePage profilePage = homePage.clickProfile();
        profilePage.clickMyArticles();
        ArticleDetailsPage articleDetailsPage = profilePage.clickLastPost();
        homePage = articleDetailsPage.clickDeleteButton();
        profilePage = homePage.clickProfile();

        WebElement myArticlesPosts = driver.findElement(By.cssSelector(".article-preview"));
        assertThat(myArticlesPosts.getText()).isEqualTo("No articles are here... yet.");

    }

    public WebElement getDatePost () {
        return driver.findElement(By.cssSelector("#main > div > div > div.banner > div > div > div > span"));
    }
}
