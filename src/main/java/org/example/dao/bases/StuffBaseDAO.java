package org.example.dao.bases;

import org.example.models.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface StuffBaseDAO<T> {

    public abstract T getRandomOfEachType(String type) throws SQLException;
    public abstract List<T> getRandomSet() throws SQLException;

}
