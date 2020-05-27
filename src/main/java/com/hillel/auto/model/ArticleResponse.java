package com.hillel.auto.model;

import lombok.Data;

@Data
public class ArticleResponse {

    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
