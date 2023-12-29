package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.dao.bases.PowerBaseDAO;
import org.example.models.Equipment;
import org.example.models.Power;
import org.example.models.StuffType;
import org.example.models.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PowerDAO extends BaseDAO<Power> implements PowerBaseDAO<Power> {

    public PowerDAO(Connection connection) {
        super(connection);
    }


    // get all powers by team name with PowerBaseDAO
    @Override
    public List<Power> getAllPowersByTeam(String teamSide) throws SQLException {
        List<Power> teamPowers = new ArrayList<>();
        try {
            request = "SELECT * FROM powers WHERE team = ? ORDER BY RANDOM() LIMIT 5";
            statement = _connection.prepareStatement(request);
            statement.setString(1,teamSide);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                Team team = Team.valueOf(resultSet.getString("team"));
                teamPowers.add(new Power(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("attack_points"),
                        resultSet.getInt("health_restorer"),
                        team



                ));
            }
        }catch (SQLException e){
            e.printStackTrace();

        }

        return teamPowers;

    }

    // CRUD with BaseDAO

    @Override
    public boolean save(Power element) throws SQLException {
        request = "INSERT INTO powers (name,attack_points,health_restorer,team)" +
                "VALUES (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setInt(2, element.getAttackPoints());
        statement.setInt(3, element.getHealthRestorer());


        switch (element.getTeam()) {
            case SITH -> {
                statement.setString(4, "SITH");
                break;
            }
            case REPUBLIC -> {
                statement.setString(4, "REPUBLIC");
                break;
            }
        }
        int nbRows = statement.executeUpdate();
        if (resultSet.next()) {
            element.setId(resultSet.getInt(1));
        }
        return nbRows == 1;
    }

    @Override
    public boolean update(Power element) throws SQLException {
        request = "UPDATE powers SET name = ?, attack_points = ?, health_restorer = ?, team = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setInt(2, element.getAttackPoints());
        statement.setInt(3, element.getHealthRestorer());
        switch (element.getTeam()) {
            case SITH -> {
                statement.setString(4, "SITH");
                break;
            }
            case REPUBLIC -> {
                statement.setString(4, "REPUBLIC");
                break;
            }

        }
        statement.setInt(5, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;    }

    @Override
    public boolean delete(Power element) throws SQLException {
        request = "DELETE FROM powers WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public Power getById(int id) throws SQLException {
        Power power = null;
        request = "SELECT * FROM powers WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {

            Team team = Team.valueOf(resultSet.getString("team"));

            power = new Power(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("attack_points"),
                    resultSet.getInt("health_restorer"),
                    team

            );
        }
        return power;
    }

    @Override
    public List<Power> getAll() throws SQLException {
        List<Power> result = new ArrayList<>();
        request = "SELECT * FROM powers ";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {

            Team team = Team.valueOf(resultSet.getString("team"));

            Power power = new Power(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("attack_points"),
                    resultSet.getInt("health_restorer"),
                    team
            );
            result.add(power);
        }
        return result;
    }


}
