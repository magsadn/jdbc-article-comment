package com.magsad.repository;

import com.magsad.config.DBConnection;
import com.magsad.model.Article;
import com.magsad.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private static final DBConnection dbConnection = new DBConnection();
    private static final ArticleRepository articleRepository = new ArticleRepository();

    public List<Category> findAll(){
        List<Category> categoryList = new ArrayList<>();
        try(Connection connection =dbConnection.getConnection()) {
            String sqlQuerySelectAll = "select * from categories order by id";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()){
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                categoryList.add(category);
            }
        } catch (SQLException throwrable) {
            System.out.println(throwrable.getMessage());
        }
        return categoryList;
    }

    public Category findById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from categories where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                Category category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                category.setArticleList(articleRepository.findByCategory(category));
                return category;
            }
        } catch (SQLException throwable) {
            throwable.getMessage();
        }
        return null;
    }

    public int save(Category category) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into categories values (?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
            psInsert.setInt(1, category.getId());
            psInsert.setString(2,category.getName());
            queryResult = psInsert.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d category was inserted!\n",category.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int update(Category category) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update categories set name=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setInt(1, category.getId());
            psUpdate.setString(2, category.getName());
            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d category was updated!\n",category.getId());
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Category category) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from categories where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setInt(1, category.getId());//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d category was deleted!\n",category.getId());
            }
        } catch (SQLException throwable){
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public List<Category> findByArticle(Article article) {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAllByDepart =  "select * from category_article ca\n" +
                                                "join categories c on c.id = ca.category_id\n" +
                                                "where ca.article_id=?";

            PreparedStatement psSelectAllByDepart = connection.prepareStatement(sqlQuerySelectAllByDepart);
            psSelectAllByDepart.setInt(1, article.getId());
            ResultSet resultSet = psSelectAllByDepart.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }
}
