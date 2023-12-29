package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.dao.bases.PowerBaseDAO;
import org.example.models.Jedi;
import org.example.models.Power;
import org.example.models.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JediDAO extends BaseDAO<Jedi>  {

    public JediDAO(Connection connection) {
        super(connection);
    }

    // CRUD with BaseDAO

    @Override
    public boolean save(Jedi element) throws SQLException {
        request = "INSERT INTO characters (name, light_saber, health, hasForce,team)" +
                "VALUES (?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());
        statement.setInt(3, element.getHealthPoints());
        statement.setBoolean(4, element.isHasForce());

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
    public boolean update(Jedi element) throws SQLException {
        request = "UPDATE characters SET name = ?, light_saber = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setBoolean(2, element.isLight_saber());

        statement.setInt(3, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public boolean delete(Jedi element) throws SQLException {
        request = "DELETE FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
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