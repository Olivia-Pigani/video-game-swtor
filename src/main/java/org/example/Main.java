package org.example;

public class Main {
    public static void main(String[] args) {


//
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = DatabaseSingleton.getConnection();
//            statement = connection.createStatement();
//            String query = "SELECT * FROM characters";
//            resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String nom = resultSet.getString("name");
//
//                System.out.println("ID: " + id + ", Nom: " + nom);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) resultSet.close();
//                if (statement != null) statement.close();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        Game game = Game.getInstance();






    }}
