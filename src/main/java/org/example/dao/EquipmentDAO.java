package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.dao.bases.StuffBaseDAO;
import org.example.models.Equipment;
import org.example.models.StuffType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO extends BaseDAO<Equipment> implements StuffBaseDAO<Equipment> {

    public EquipmentDAO(Connection connection) {
        super(connection);
    }

    // get random stuff from StuffBaseDAO

    @Override
    public Equipment getRandomOfEachType(String type) throws SQLException {
        try {


            String request = "SELECT * FROM equipments WHERE stuff_type = ? ORDER BY RANDOM() LIMIT 1";
            PreparedStatement preparedStatement = _connection.prepareStatement(request);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int attackPoints = resultSet.getInt("attack_points");
                int healthRestorer = resultSet.getInt("health_restorer");
                String stuffType = resultSet.getString("stuff_type");

                return new Equipment(name, attackPoints, healthRestorer, StuffType.valueOf(stuffType));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Equipment> getRandomSet() throws SQLException {
        List<Equipment> equipments = new ArrayList<>();
        try {
            equipments.add(getRandomOfEachType("WEAPON"));
            equipments.add(getRandomOfEachType("GRENADE"));
            equipments.add(getRandomOfEachType("MEDKIT"));

        }catch (SQLException e){
            e.printStackTrace();
        }
        return equipments;

    }


// CRUD from BaseDAO

    @Override
    public boolean save(Equipment element) throws SQLException {
        request = "INSERT INTO equipments (name, attack_points, health_restorer, stuff_type)" +
                "VALUES (?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, element.getName());
        statement.setInt(2, element.getAttackPoints());
        statement.setInt(3, element.getHealthRestorer());

        switch (element.getStuffType()) {
            case MEDKIT -> {
                statement.setString(4, "MEDKIT");
                break;
            }
            case WEAPON -> {
                statement.setString(4, "WEAPON");
                break;
            }
            case GRENADE -> {
                statement.setString(4, "GRENADE");
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
    public boolean update(Equipment element) throws SQLException {
        request = "UPDATE equipments SET name = ?, attack_points = ?, health_restorer = ?, stuff_type = ? WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setInt(2, element.getAttackPoints());
        statement.setInt(3, element.getHealthRestorer());
        switch (element.getStuffType()) {
            case MEDKIT -> {
                statement.setString(4, "MEDKIT");
                break;
            }
            case WEAPON -> {
                statement.setString(4, "WEAPON");
                break;
            }
            case GRENADE -> {
                statement.setString(4, "GRENADE");
                break;
            }
        }
        statement.setInt(5, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;

    }

    @Override
    public boolean delete(Equipment element) throws SQLException {
        request = "DELETE FROM equipments WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRows = statement.executeUpdate();
        return nbRows == 1;
    }

    @Override
    public Equipment getById(int id) throws SQLException {
        Equipment equipment = null;
        request = "SELECT * FROM equipments WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {

            StuffType stuffType = StuffType.valueOf(resultSet.getString("stuff_type"));

            equipment = new Equipment(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("attack_points"),
                    resultSet.getInt("health_restorer"),
                    stuffType

            );
        }
        return equipment;
    }

    @Override
    public List<Equipment> getAll() throws SQLException {
        List<Equipment> result = new ArrayList<>();
        request = "SELECT * FROM equipments";
        statement = _connection.prepareStatement(request);
        resultSet = statement.executeQuery();
        while (resultSet.next()) {

            StuffType stuffType = StuffType.valueOf(resultSet.getString("stuff_type"));

            Equipment equipment = new Equipment(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("attack_points"),
                    resultSet.getInt("health_restorer"),
                    stuffType
            );
            result.add(equipment);
        }
        return result;
    }
}
