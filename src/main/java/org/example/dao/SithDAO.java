package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.models.Sith;
import org.example.models.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SithDAO extends BaseDAO<Sith> {
    public SithDAO(Connection connection) {
        super(connection);
    }


    // Pick one sith in the database randomly

    public Sith getOneRandomSith() throws SQLException{
        Sith randomSith = null;
        try {
            request = "SELECT * FROM characters WHERE team = 'SITH' ORDER BY RANDOM() LIMIT 1";
            PreparedStatement preparedStatement = _connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Sith.Builder sithBuilder = new Sith.Builder()
                        .name(resultSet.getString("name"))
                        .light_saber(resultSet.getBoolean("light_saber"))
                        .healthPoints(resultSet.getInt("health"));

                randomSith = sithBuilder.build();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return randomSith;
    }



// CRUD with BaseDAO


    @Override
    public boolean save(Sith element) throws SQLException {
        request = "INSERT INTO characters (name, light_saber, health, hasForce,team)" +
                "VALUES (?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());
        statement.setInt(3, element.getHealthPoints());
        statement.setBoolean(4, element.isHasForce());

        if (element.getTeam().equals(Team.SITH)) {
            statement.setString(5, "SITH");
        }

        int nbRows = statement.executeUpdate();
        if (resultSet.next()) {
            element.setId(resultSet.getInt(1));
        }
        return nbRows == 1;
    }

    @Override
    public boolean update(Sith element) throws SQLException {
        request = "UPDATE characters SET name = ?, light_saber = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());

        statement.setInt(3, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Sith element) throws SQLException {
        request = "DELETE FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public Sith getById(int id) throws SQLException {
        Sith sith = null;
        request = "SELECT * FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {

            Sith.Builder sithBuilder = new Sith.Builder()
                    .name(resultSet.getString("name"))
                    .light_saber(resultSet.getBoolean("light_saber"))
                    .healthPoints(resultSet.getInt("health"));

            // no need to get hasForce and team, we already know that by default.

            sith = sithBuilder.build();

        }
        return sith;
    }

    @Override
    public List<Sith> getAll() throws SQLException {
        List<Sith> result = new ArrayList<>();
        request = "SELECT * FROM characters";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {


            Sith.Builder sithBuilder = new Sith.Builder()
                    .name(resultSet.getString("name"))
                    .light_saber(resultSet.getBoolean("light_saber"))
                    .healthPoints(resultSet.getInt("health"));

            Sith sith = sithBuilder.build();

            result.add(sith);

        }
        return result;
    }
}
