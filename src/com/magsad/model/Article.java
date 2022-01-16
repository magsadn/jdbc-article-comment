package com.magsad.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Article {
    private Integer id;
    private String headline;
    private String articleAbstract;
    private String mainText;
    private LocalDate dateCreated;
    private LocalDate dateAmended;
    private String postedBy;
    private Boolean makePublic;
    private Integer views;
    private Type type;

    private List<Comment> commentList;

    private List<Category> categoryList;

    public Article(Integer id, String headline, String articleAbstract, String mainText, LocalDate dateCreated, LocalDate dateAmended, String postedBy, Boolean makePublic, Integer views, Type type, List<Comment> commentList, List<Category> categoryList) {
        this.id = id;
        this.headline = headline;
        this.articleAbstract = articleAbstract;
        this.mainText = mainText;
        this.dateCreated = dateCreated;
        this.dateAmended = dateAmended;
        this.postedBy = postedBy;
        this.makePublic = makePublic;
        this.views = views;
        this.type = type;
        this.commentList = commentList;
        this.categoryList = categoryList;
    }

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateAmended() {
        return dateAmended;
    }

    public void setDateAmended(LocalDate dateAmended) {
        this.dateAmended = dateAmended;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Boolean getMakePublic() {
        return makePublic;
    }

    public void setMakePublic(Boolean makePublic) {
        this.makePublic = makePublic;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(headline, article.headline) && Objects.equals(articleAbstract, article.articleAbstract) && Objects.equals(mainText, article.mainText) && Objects.equals(dateCreated, article.dateCreated) && Objects.equals(dateAmended, article.dateAmended) && Objects.equals(postedBy, article.postedBy) && Objects.equals(makePublic, article.makePublic) && Objects.equals(views, article.views) && Objects.equals(type, article.type) && Objects.equals(commentList, article.commentList) && Objects.equals(categoryList, article.categoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, headline, articleAbstract, mainText, dateCreated, dateAmended, postedBy, makePublic, views, type, commentList, categoryList);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", headline='" + headline + '\'' +
                ", articleAbstract='" + articleAbstract + '\'' +
                ", mainText='" + mainText + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateAmended=" + dateAmended +
                ", postedBy='" + postedBy + '\'' +
                ", makePublic=" + makePublic +
                ", views=" + views +
                ", typeId=" + type.getId() +
                ", commentList=" + commentList +
                ", categoryList=" + categoryList +
                '}';
    }
}
