package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton {



    private static final String dbName = System.getenv("POSTGRES_DB");
    private static final String USER = System.getenv("POSTGRES_USER");
    private static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");
    // private static final String PASSWORD = "root";
    private static final String URI = "jdbc:postgresql://db:5432/" + dbName;
    //private static final String USER = "postgres";
    private static volatile Connection connection = null;

    private DatabaseSingleton() {
    }

    public static Connection getConnection() {

        System.out.println(URI);
        System.out.println(USER);
        System.out.println(PASSWORD);

        if (connection == null) {
            synchronized (DatabaseSingleton.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(URI,USER,PASSWORD);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            synchronized (DatabaseSingleton.class) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        connection = null;
                    }
                }
            }
        }
    }
}
