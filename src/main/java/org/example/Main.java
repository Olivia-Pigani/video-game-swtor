package org.example;

import org.example.utils.DatabaseSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseSingleton.getConnection();
            statement = connection.createStatement();
            String query = "SELECT * FROM characters"; // Remplacez 'ma_table' par le nom de votre table
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // Exemple : Si votre table a des colonnes 'id' et 'nom'
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
                // Ne fermez pas la connexion si vous l'utilisez ailleurs dans l'application
                // DatabaseSingleton.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}