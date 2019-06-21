package com.ramyhelow.newsapi.Model;

public class Article {
    private String article_image;
    private String article_title;
    private String article_description;

    public Article(String article_image, String article_title, String article_description) {
        this.article_image = article_image;
        this.article_title = article_title;
        this.article_description = article_description;
    }

    public String getArticle_image() {
        return article_image;
    }

    public void setArticle_image(String article_image) {
        this.article_image = article_image;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_description() {
        return article_description;
    }

    public void setArticle_description(String article_description) {
        this.article_description = article_description;
    }
}
