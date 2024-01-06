package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.dao.bases.PowerBaseDAO;
import org.example.models.Jedi;
import org.example.models.Power;
import org.example.models.Sith;
import org.example.models.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JediDAO extends BaseDAO<Jedi>  {

    public JediDAO(Connection connection) {
        super(connection);
    }




    // Pick one jedi in the database randomly

    public Jedi getOneRandomJedi() throws SQLException{
        Jedi randomJedi = null;
        try {
            request = "SELECT * FROM characters WHERE team = 'REPUBLIC' ORDER BY RANDOM() LIMIT 1";
            PreparedStatement preparedStatement = _connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Jedi.Builder jediBuilder = new Jedi.Builder()
                        .name(resultSet.getString("name"))
                        .light_saber(resultSet.getBoolean("light_saber"))
                        .healthPoints(resultSet.getInt("health"));

                randomJedi = jediBuilder.build();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return randomJedi;
    }



    // CRUD with BaseDAO

    @Override
    public boolean save(Jedi element) throws SQLException {
        request = "INSERT INTO characters (name, light_saber, health, hasForce,team)" +
                "VALUES (?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());


        if (element.getTeam().equals(Team.REPUBLIC)) {
            statement.setString(5, "REPUBLIC");
        }

        int nbRows = statement.executeUpdate();
        if (resultSet.next()) {
            element.setId(resultSet.getInt(1));
        }
        return nbRows == 1;
    }

    @Override
    public Jedi update(Jedi element) throws SQLException {
        request = "UPDATE characters SET name = ?, light_saber = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());

        statement.setInt(3, element.getId());
        int nbRows = statement.executeUpdate();
        if (nbRows == 1){

            return element;
        }
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        request = "DELETE FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        int nbRows = statement.executeUpdate();
        if (nbRows == 1){
            System.out.println("The character has been deleted ! ");
        }
        System.out.println("Something wrong during the deletion ! ");
    }

    @Override
    public Jedi getById(int id) throws SQLException {
        Jedi jedi = null;
        request = "SELECT * FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {

            Jedi.Builder jediBuilder = new Jedi.Builder()
                    .name(resultSet.getString("name"))
                    .light_saber(resultSet.getBoolean("light_saber"))
                    .healthPoints(resultSet.getInt("health"));

            // no need to get hasForce and team, we already know that by default.

            jedi = jediBuilder.build();

        }
        return jedi;
    }

    @Override
    public List<Jedi> getAll() throws SQLException {
        List<Jedi> result = new ArrayList<>();
        request = "SELECT * FROM characters";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {


                Jedi.Builder jediBuilder = new Jedi.Builder()
                        .name(resultSet.getString("name"))
                        .light_saber(resultSet.getBoolean("light_saber"))
                        .healthPoints(resultSet.getInt("health"));

                Jedi jedi = jediBuilder.build();

                result.add(jedi);

        }
        return result;
    }





}