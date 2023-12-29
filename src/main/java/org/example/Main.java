package org.example;

import org.example.utils.DatabaseSingleton;
import org.example.models.GameSingleton;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
      
      
      
        GameSingleton game = GameSingleton.getInstance();
//        GameSingleton game1 = GameSingleton.getInstance();

//        System.out.println(game1);
        System.out.println(game);
      
      
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseSingleton.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM characters";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("name");

                System.out.println("ID: " + id + ", Nom: " + nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



