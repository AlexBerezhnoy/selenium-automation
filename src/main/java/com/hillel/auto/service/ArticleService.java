package com.hillel.auto.service;

import com.hillel.auto.model.Article;
import com.hillel.auto.model.ArticleResponse;
import io.restassured.RestAssured;

public class ArticleService extends ApiService  {

    private String token;

    public ArticleService(String token) {
        this.token = token;

    }

    public Article createArticle(Article article) {
        return RestAssured
                .given()
                .header("Authorization", "Token " + token)
                .body(article)
                .when()
                .post("/articles")
                .then()
                .statusCode(200)
                .extract().body()
                .as(ArticleResponse.class)
                .getArticle();
    }

    public void deleteArticle(String slug) {
        RestAssured
                .given()
                .header("Authorization", "Token " + token)
                .when()
                .delete("/articles/" + slug)
                .then()
                .statusCode(200);
    }


}
