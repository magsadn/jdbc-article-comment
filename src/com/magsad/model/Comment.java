package com.magsad.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Comment {
    private Integer id ;
    private String name;
    private String email;
    private String text;
    private LocalDate datePosted;
    private Boolean isReply;
    private Boolean publish;

    private Article article;

    public Comment(Integer id, String name, String email, String text, LocalDate datePosted, Boolean isReply, Boolean publish, Article article) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
        this.datePosted = datePosted;
        this.isReply = isReply;
        this.publish = publish;
        this.article = article;
    }

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public Boolean getReply() {
        return isReply;
    }

    public void setReply(Boolean reply) {
        isReply = reply;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(name, comment.name) && Objects.equals(email, comment.email) && Objects.equals(text, comment.text) && Objects.equals(datePosted, comment.datePosted) && Objects.equals(isReply, comment.isReply) && Objects.equals(publish, comment.publish) && Objects.equals(article, comment.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, text, datePosted, isReply, publish, article);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                ", datePosted=" + datePosted +
                ", isReply=" + isReply +
                ", publish=" + publish +
                ", articleId=" + article.getId() +
                '}';
    }
}
