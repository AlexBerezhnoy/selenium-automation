package com.hillel.auto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Article {

    @JsonProperty("slug")
    private String slug;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("body")
    private String body;
    @JsonProperty("tagList")
    private List<String> tagList = null;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("favorited")
    private Boolean favorited;
    @JsonProperty("favoritesCount")
    private Integer favoritesCount;
    @JsonProperty("author")
    private Author author;


}
