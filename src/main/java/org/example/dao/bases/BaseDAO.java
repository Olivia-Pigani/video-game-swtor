package org.example.dao.bases;

import org.example.utils.DatabaseSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {

    protected Connection _connection;
    protected PreparedStatement statement;
    protected String request;
    protected ResultSet resultSet;


    protected BaseDAO(Connection connection) {
        _connection=connection;
    }


    public abstract boolean save(T element) throws SQLException;
    public abstract T update(T element) throws SQLException;
    public abstract void delete(int id) throws SQLException;

    public abstract T getById(int id) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

}
