package org.example.dao;

import org.example.dao.bases.BaseDAO;
import org.example.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CloneDAO extends BaseDAO<IsClone> {


    public CloneDAO(Connection connection) {
        super(connection);
    }


    @Override
    public boolean save(IsClone element) throws SQLException {
        String query = "INSERT INTO characters (name, light_saber, health, hasForce, team) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = this._connection.prepareStatement(query);

        if (element instanceof RepublicSoldier) {
            RepublicSoldier republicSoldier = (RepublicSoldier) element;

            statement.setString(1, republicSoldier.getName());
            statement.setBoolean(2, republicSoldier.isLightSaber());
            statement.setInt(3, republicSoldier.getHealthPoints());
            statement.setBoolean(4, republicSoldier.isHasForce());
            statement.setString(5, republicSoldier.getTeam().toString());

        } else if (element instanceof Trooper) {
            Trooper trooper = (Trooper) element;

            statement.setString(1, trooper.getName());
            statement.setBoolean(2, trooper.isLightSaber());
            statement.setInt(3, trooper.getHealthPoints());
            statement.setBoolean(4, trooper.isHasForce());
            statement.setString(5, trooper.getTeam().toString());
        }


        int nbRows = statement.executeUpdate();
        return nbRows > 0;
    }


    // no need to update, there are clones
    @Override
    public boolean update(IsClone element) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(IsClone element) throws SQLException {
        String query = "DELETE FROM characters WHERE id = ?";
        PreparedStatement statement = this._connection.prepareStatement(query);

        if (element instanceof RepublicSoldier) {
            RepublicSoldier republicSoldier = (RepublicSoldier) element;
            statement.setInt(1, republicSoldier.getId());

        } else if (element instanceof Trooper) {
            Trooper trooper = (Trooper) element;
            statement.setInt(1, trooper.getId());
        }


        int affectedRows = statement.executeUpdate();
        return affectedRows > 0;
    }

    @Override
    public IsClone getById(int id) throws SQLException {
        IsClone isClone = null;
        request = "SELECT * FROM characters WHERE id = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String team = resultSet.getString("team");
            if (team.equals(Team.REPUBLIC.toString())) {
                RepublicSoldier republicSoldier = new RepublicSoldier();
                return republicSoldier;
            } else if (team.equals(Team.SITH.toString())) {
                Trooper trooper = new Trooper();
                return trooper;
            }
        }
        return null;
    }


    @Override
    public List<IsClone> getAll() throws SQLException {
        List<IsClone> clones = new ArrayList<>();
        String query = "SELECT * FROM characters";
        PreparedStatement statement = this._connection.prepareStatement(query);

        resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String team = resultSet.getString("team");

            if (team.equals(Team.REPUBLIC.toString())) {
                RepublicSoldier soldier = new RepublicSoldier();

                clones.add(soldier);
            } else if (team.equals(Team.SITH.toString())) {

                Trooper trooper = new Trooper();

                clones.add(trooper);
            }
        }
        return clones;
    }
}
