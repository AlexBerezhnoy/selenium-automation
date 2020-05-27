package io.react.realworld;

import com.hillel.auto.model.Article;
import com.hillel.auto.model.User;
import com.hillel.auto.page.object.*;
import com.hillel.auto.service.ArticleService;
import com.hillel.auto.service.UserService;
import com.hillel.auto.utils.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class NewPostTest extends TestBase {
    private UserService userService = new UserService();
    private ArticleService articleService;
    private User user;
    private Article article;
    private HomePage homePage;
    String title = "New Test Post";
    String about = "For testing";
    String postText = "Post For testing";
    String tags = "Tags!";

    @BeforeMethod
    public void login() {
        user = userService.userRegistration();
        articleService = new ArticleService(user.getToken());

        article = articleService.createArticle(getArticle());
        clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);

        assertThat(loginPage.getPageTitle()).isEqualTo("Sign In");

        homePage = loginPage.login(user.getEmail(), user.getPassword());
        assertThat(homePage.isUserLoggedIn(user.getUsername())).isTrue();
    }

    @AfterMethod
    public void cleanData() {
        articleService.deleteArticle(article.getSlug());
    }
    @Test(priority = 1 )
    public void createPost () {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE MMM dd yyyy").withLocale(Locale.ENGLISH);
        String nowDate = date.format(formatter);
        NewPostPage newPostPage = homePage.clickNewPost();
        newPostPage.inputArticleTitle(title);
        newPostPage.inputWhatArticleAbout(about);
        newPostPage.inputArticle(postText);
        newPostPage.inputTags(tags);
        ArticleDetailsPage articleDetailsPage = newPostPage.clickPublishArticleBtn();
        assertThat(articleDetailsPage.getPostText()).isEqualTo(postText);
        String datePost = articleDetailsPage.getDatePost().getText();
        assertThat(datePost).isEqualTo(nowDate);
    }

    @Test(priority = 3 )
    public void editPost () {
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

    @Test (priority = 4 )
    public void deletePost () {
        ProfilePage profilePage = homePage.clickProfile();
        profilePage.clickMyArticles();
        ArticleDetailsPage articleDetailsPage = profilePage.clickLastPost();
        homePage = articleDetailsPage.clickDeleteButton();
        profilePage = homePage.clickProfile();
        assertThat(profilePage.getMyArticlePreview().getText()).isEqualTo("No articles are here... yet.");
    }

    @Test (priority = 2 )
    public void checkArticleSize() {
        ProfilePage profilePage = homePage.clickProfile();
        assertThat(profilePage.getArticleSize()).isGreaterThan(0);
    }

    private Article getArticle() {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(about);
        article.setBody(postText);
        article.setTagList(Arrays.asList("test", "hoho"));
        return article;
    }
}