package io.react.realworld.api;

import com.hillel.auto.model.*;
import com.hillel.auto.service.UserService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;

public class CommentsTest {
    private UserService userService = new UserService();
    private User user;
    Article newArticle;
    Comment comment;

    @BeforeClass
    public void setUp () {
        user = userService.userRegistration();
        RestAssured.baseURI = "https://conduit.productionready.io";
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder()
                        .setAccept(ContentType.JSON)
                        .setContentType(ContentType.JSON)
                        .addHeader("Authorization", "Token " + user.getToken())
                        .log(LogDetail.ALL)
                        .build();
        newArticle = createArticle ();
    }

    @Test
    public void getFavoritesTest() {
        comment = createComments();

        CommentApi[] commentApi = RestAssured
                .given()
                .when()
                .get("/articles/"+getSlug()+"/comments")
                .then()
                .statusCode(200)
                .extract().body()
                .as(CommentResponce.class)
                .getCommentApi();

        System.out.println(commentApi);
    }

    @Test
    public void postFavoritesTest() {
        Comment comment = new Comment();
        comment.setBody("First comment!");
        CommentApi commentApi = new CommentApi(comment);

        comment = RestAssured
                .given()
                .body(commentApi)
                .when()
                .post("/articles/"+getSlug()+"/comments")
                .then()
                .statusCode(200)
                .extract().body()
                .as(CommentApi.class)
                .getComment();

        System.out.println(comment);
    }

    @Test
    public void deleteFavoritesTest() {
        comment = createComments();
        comment = RestAssured
                .given()
                .when()
                .delete("/articles/"+getSlug()+"/comments/"+comment.getId())
                .then()
                .statusCode(200)
                .extract().body()
                .as(CommentApi.class)
                .getComment();

        System.out.println(comment);
    }


    public String getSlug() {
        newArticle = createArticle ();
        return  newArticle.getSlug();
    }

    public Comment createComments () {
        comment = new Comment();
        comment.setBody("First comment!");
        CommentApi commentApi = new CommentApi(comment);

        comment = RestAssured
                .given()
                .body(commentApi)
                .when()
                .post("/articles/"+getSlug()+"/comments")
                .then()
                .statusCode(200)
                .extract().body()
                .as(CommentApi.class)
                .getComment();
        return comment;
    }

    public Article createArticle () {

        Article article = new Article();
        article.setTitle("First article");
        article.setDescription("Super Article desc");
        article.setBody("test hoho");
        article.setTagList(Arrays.asList("test", "hoho"));

        newArticle = RestAssured
                .given()
                .body(article)
                .when()
                .post("/articles")
                .then()
                .statusCode(200)
                .extract().body()
                .as(ArticleResponse.class)
                .getArticle();
        return newArticle;
    }
}
