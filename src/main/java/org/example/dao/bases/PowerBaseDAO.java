package org.example.dao.bases;

import java.sql.SQLException;
import java.util.List;

public interface PowerBaseDAO<T> {

    public abstract List<T> getAllPowersByTeam(String teamSide) throws SQLException;


}
