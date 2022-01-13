package com.magsad.repository;

import com.magsad.config.DBConnection;
import com.magsad.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeRepository {
    private static final DBConnection dbConnection = new DBConnection();
    private static final ArticleRepository articleRepository = new ArticleRepository();

    public List<Type> findAll() {
        List<Type> typeList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectAll = "select * from types order by id";
            PreparedStatement psSelectAll = connection.prepareStatement(sqlQuerySelectAll);
            ResultSet resultSet = psSelectAll.executeQuery();
            while (resultSet.next()) {
                Type a = new Type(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                typeList.add(a);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());;
        }
        return typeList;
    }

    public Type findById(int id) {
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQuerySelectById = "select * from types where id = ?";
            PreparedStatement psSelectById = connection.prepareStatement(sqlQuerySelectById);
            psSelectById.setInt(1,id);
            ResultSet resultSet = psSelectById.executeQuery();
            if (resultSet.next()){
                Type type = new Type(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new ArrayList<>()
                );
                type.setArticleList(articleRepository.findByType(type));
                return type;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return null;
    }

    public int save(Type type) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryInsert = "insert into types values (?,?)";
            PreparedStatement psInsert = connection.prepareStatement(sqlQueryInsert);
            psInsert.setInt(1, type.getId());
            psInsert.setString(2, type.getName());
            queryResult = psInsert.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d type was inserted!\n",type.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int update(Type type) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryUpdate = "update types set name=? where id=?";
            PreparedStatement psUpdate = connection.prepareStatement(sqlQueryUpdate);
            psUpdate.setString(1, type.getName());
            psUpdate.setInt(2, type.getId());
            queryResult = psUpdate.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d department was updated!\n",type.getId());
            }

        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

    public int delete(Type type) {
        int queryResult = 0;
        try (Connection connection = dbConnection.getConnection()) {
            String sqlQueryDelete = "delete from types where id=?";
            PreparedStatement psDelete = connection.prepareStatement(sqlQueryDelete);
            psDelete.setInt(1, type.getId());//1ci ? isaresinin evezine yazilir.2ci sual isaresi yoxdur.
            queryResult = psDelete.executeUpdate();
            System.out.println(queryResult);
            if (queryResult != 0) {
                System.out.format("Id %d department was deleted!\n",type.getId());
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        }
        return queryResult;
    }

}
