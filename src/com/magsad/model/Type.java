package com.magsad.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Type {
    private Integer id;
    private String name;
    private List<Article> articleList = new ArrayList<>();

    public Type(Integer id, String name, List<Article> articleList) {
        this.id = id;
        this.name = name;
        this.articleList = articleList;
    }

    public Type() {
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

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type that = (Type) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(articleList, that.articleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, articleList);
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articleList=" + articleList +
                '}';
    }
}
