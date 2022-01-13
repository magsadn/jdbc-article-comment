package com.magsad.repository;

import com.magsad.config.DBConnection;
import com.magsad.model.Article;
import com.magsad.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    private static final DBConnection dbConnection = new DBConnection();
    private static final CommentRepository commentRepository = new CommentRepository();

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAll = "select * from articles order by id";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()) {
                Article article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("headline"),
                        resultSet.getString("article_abstract"),
                        resultSet.getString("main_text"),
                        resultSet.getDate("date_created").toLocalDate(),
                        resultSet.getDate("date_amended").toLocalDate(),
                        resultSet.getString("posted_by"),
                        resultSet.getBoolean("make_public"),
                        resultSet.getInt("views"),
//                        typeRepository.findById(resultSet.getInt("type_id")),
                        findTypeById(resultSet.getInt("type_id")),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
                articleList.add(article);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return articleList;
    }

    public Article findById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from articles where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1, id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()) {
                Article article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("headline"),
                        resultSet.getString("article_abstract"),
                        resultSet.getString("main_text"),
                        resultSet.getDate("date_created").toLocalDate(),
                        resultSet.getDate("date_amended").toLocalDate(),
                        resultSet.getString("posted_by"),
                        resultSet.getBoolean("make_public"),
                        resultSet.getInt("views"),
//                        typeRepository.findById(resultSet.getInt("type_id")),
                        findTypeById(resultSet.getInt("type_id")),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
//                article.setCommentList(commentRepository.findByArticle(article));
                return article;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

    public int save(Article art) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into articles values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
            psInsert.setInt(1, art.getId());
            psInsert.setString(2, art.getHeadline());
            psInsert.setString(3, art.getArticleAbstract());
            psInsert.setString(4, art.getMainText());
            psInsert.setDate(5, Date.valueOf(art.getDateCreated()));
            psInsert.setDate(6, Date.valueOf(art.getDateAmended()));
            psInsert.setString(7, art.getPostedBy());
            psInsert.setBoolean(8, art.getMakePublic());
            psInsert.setInt(9, art.getViews());
            psInsert.setInt(10, art.getType().getId());
            queryResult = psInsert.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d article was inserted!\n", art.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int update(Article art) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update articles set headline=?,article_abstract=?,main_text=?,date_created=?,date_amended=?,posted_by=?,make_public=?,views=?,type_id=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setInt(1, art.getId());
            psUpdate.setString(2, art.getHeadline());
            psUpdate.setString(3, art.getArticleAbstract());
            psUpdate.setString(4, art.getMainText());
            psUpdate.setDate(5, Date.valueOf(art.getDateCreated()));
            psUpdate.setDate(6, Date.valueOf(art.getDateAmended()));
            psUpdate.setString(7, art.getPostedBy());
            psUpdate.setBoolean(8, art.getMakePublic());
            psUpdate.setInt(9, art.getViews());
            psUpdate.setInt(10, art.getType().getId());
            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d article was updated!\n", art.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Article art) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from articles where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setInt(1, art.getId());//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d article was deleted!\n", art.getId());
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public List<Article> findByType(Type type) {
        List<Article> articleList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectByType = "select * from articles where type_id=?";
            PreparedStatement psSelectAllByDepart = connection.prepareStatement(sqlQuerySelectByType);
            psSelectAllByDepart.setInt(1, type.getId());
            ResultSet resultSet = psSelectAllByDepart.executeQuery();
            while (resultSet.next()) {
                Article a = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("headline"),
                        resultSet.getString("article_abstract"),
                        resultSet.getString("main_text"),
                        resultSet.getDate("date_created").toLocalDate(),
                        resultSet.getDate("date_amended").toLocalDate(),
                        resultSet.getString("posted_by"),
                        resultSet.getBoolean("make_public"),
                        resultSet.getInt("views"),
                        findTypeById(resultSet.getInt("type_id")),
                        new ArrayList<>(),
                        new ArrayList<>()
                );
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return articleList;
    }

    public Type findTypeById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from types where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1, id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()) {
                Type type = new Type(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                return type;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }


    public List<Article> findByCategory(int id) {
        List<Article> articleList = new ArrayList<>();
        Array array;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAllByDepart = "select * from category_article where category_id=? order by id";
            PreparedStatement psSelectAllByDepart = connection.prepareStatement(sqlQuerySelectAllByDepart);
            psSelectAllByDepart.setInt(1, id);
            ResultSet resultSet = psSelectAllByDepart.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getArray("article_id"));
                System.out.println("sout");
                Article a = new Article(
                        resultSet.getInt("article_id"), null, null, null, null, null, null, null, null, null, null, null
                );
                articleList.add(a);
                articleList.forEach(art -> findById(art.getId()));
//                    articleList.add(findById(resultSet.getInt("article_id")));
                return articleList;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }
}
