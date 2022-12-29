package com.phanison;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

    private static String MYSQL_HOST;
    private static String MYSQL_PORT;
    private static String MYSQL_USER;
    private static String MYSQL_PASSWORD;
    private static String MYSQL_DB_NAME;

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        Env env = new Env();

        MYSQL_HOST = env.get("MYSQL_HOST");
        MYSQL_PORT = env.get("MYSQL_PORT");
        MYSQL_USER = env.get("MYSQL_USER");
        MYSQL_PASSWORD = env.get("MYSQL_PASSWORD");
        MYSQL_DB_NAME = env.get("MYSQL_DB_NAME");

        String databaseURL = "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB_NAME;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(databaseURL, MYSQL_USER, MYSQL_PASSWORD);
            statement = conn.createStatement();
            System.out.println("Successfully connected to database");
        } catch (Exception e) {
            System.out.println("Error while connecting to database");
        }

        try {
            resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("SQL query error");
        } finally {
            try {
                conn.close();
                System.out.println("Successfully disconnected from database");
            } catch (SQLException e) {
                System.out.println("Error while disconnecting from database");
            }
        }
    }
}
