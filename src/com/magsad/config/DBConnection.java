package com.magsad.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection=null;
    public Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5432/article-comment";
        String user = "postgres";
        String password = "";
//        System.out.println("Connecting ...");
        try {
            connection = DriverManager.getConnection(url,user,password);
//            System.out.println("Connected!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        return connection;
    }

    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
                System.out.println("Connection closed!");
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }
}
