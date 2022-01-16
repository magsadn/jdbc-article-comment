package com.magsad.repository;

import com.magsad.config.DBConnection;
import com.magsad.model.Article;
import com.magsad.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {
    private static final DBConnection dbConnection = new DBConnection();
    private static final ArticleRepository articleRepository = new ArticleRepository();

    public List<Comment> findAll() {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAll = "select * from comments";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()) {
                Comment c = new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("text"),
                        resultSet.getDate("date_posted").toLocalDate(),
                        resultSet.getBoolean("is_reply"),
                        resultSet.getBoolean("publish"),
                        findArticleById(resultSet.getInt("article_id"))
//                        articleRepository.findById(resultSet.getInt("article_id"))
                );
                commentList.add(c);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return commentList;
    }

    public Comment findById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from comments where id=?";
            PreparedStatement ps = connection.prepareStatement(sqlQuerySelectById);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("text"),
                        resultSet.getDate("date_posted").toLocalDate(),
                        resultSet.getBoolean("is_reply"),
                        resultSet.getBoolean("publish"),
                        findArticleById(resultSet.getInt("article_id"))
//                        articleRepository.findById(resultSet.getInt("article_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int save(Comment comment) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into comments values (?,?,?,?,?,?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
            psInsert.setInt(1, comment.getId());
            psInsert.setString(2, comment.getName());
            psInsert.setString(3, comment.getEmail());
            psInsert.setString(4, comment.getText());
            psInsert.setDate(5, Date.valueOf(comment.getDatePosted()));
            psInsert.setBoolean(6, comment.getReply());
            psInsert.setInt(7, comment.getArticle().getId());

            queryResult = psInsert.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.println("Inserted!");
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int update(Comment comment) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update comments set name=?,email=?,text=?,date_posted=?,is_reply=?,publish=?,article_id=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setString(1, comment.getName());
            psUpdate.setString(2, comment.getEmail());
            psUpdate.setString(3, comment.getText());
            psUpdate.setDate(4, Date.valueOf(comment.getDatePosted()));
            psUpdate.setBoolean(5, comment.getReply());
            psUpdate.setBoolean(6, comment.getPublish());
            psUpdate.setInt(7, comment.getArticle().getId());
            psUpdate.setInt(8, comment.getId());

            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d comment was updated!\n", comment.getId());
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Comment comment) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from comments where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setInt(1, comment.getId());
            queryResult = psDelete.executeUpdate();
            if (queryResult !=0){
                System.out.format("Id %d comment was deleted!",comment.getId());
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }


    public List<Comment> findByArticle(Article article){
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()){
            String sqlQuerySelectAllByDepart = "select * from comments where article_id=? order by id";
            PreparedStatement psSelectAllByDepart = connection.prepareStatement(sqlQuerySelectAllByDepart);
            psSelectAllByDepart.setInt(1,article.getId());
            ResultSet resultSet = psSelectAllByDepart.executeQuery();
            while (resultSet.next()){
                Comment c = new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("text"),
                        resultSet.getDate("date_posted").toLocalDate(),
                        resultSet.getBoolean("is_reply"),
                        resultSet.getBoolean("publish"),
                        findArticleById(resultSet.getInt("article_id"))
//                        articleRepository.findById(resultSet.getInt("article_id"))
                        );
                        commentList.add(c);
            }
            return commentList;
        }catch (SQLException throwable){
            System.out.println(throwable.getMessage());
        }
        return null;
    }

    private Article findArticleById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from articles where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                Article a  = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("headline"),
                        resultSet.getString("article_abstract"),
                        resultSet.getString("main_text"),
                        resultSet.getDate("date_created").toLocalDate(),
                        resultSet.getDate("date_amended").toLocalDate(),
                        resultSet.getString("posted_by"),
                        resultSet.getBoolean("make_public"),
                        resultSet.getInt("views"),
                        articleRepository.findTypeById(resultSet.getInt("type_id")),
                        new ArrayList<>(),
                        new ArrayList<>());
                return a;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

}
