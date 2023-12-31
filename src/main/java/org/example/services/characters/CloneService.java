package org.example.services.characters;

import org.example.dao.CloneDAO;
import org.example.dao.EquipmentDAO;
import org.example.models.*;
import org.example.services.game.composite.GameComponent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CloneService {

    private CloneDAO cloneDAO;
    private EquipmentDAO equipmentDAO;


    public CloneService(CloneDAO cloneDAO, EquipmentDAO equipmentDAO) {
        this.cloneDAO = cloneDAO;
        this.equipmentDAO = equipmentDAO;
    }

    public   List<GameComponent> produceClones(Team team, int nbOfClone ) throws SQLException {
        List<GameComponent> clones = new ArrayList<>();

        for (int i = 0; i < nbOfClone; i++) {
            if (team == Team.REPUBLIC) {
                RepublicSoldier republicSoldier = new RepublicSoldier();
                republicSoldier.setEquipments(getRandomEquipment ());
                cloneDAO.save(republicSoldier);
            } else if (team == Team.SITH) {
                Trooper trooper = new Trooper();
                trooper.setEquipments(getRandomEquipment ());
                cloneDAO.save(trooper);
            } else {
                throw new SQLException(" incorrect team name ! ");
            }
        }
        return clones;
    }

    private List<Equipment> getRandomEquipment () throws SQLException {
        return equipmentDAO.getRandomSet();
    }


    public void removeDeadCloneFromDb (IsClone isClone) throws SQLException{
        try {

            if (isClone instanceof RepublicSoldier){
                RepublicSoldier republicSoldier = (RepublicSoldier) isClone;
                if (republicSoldier.getHealthPoints()<=0){
                    cloneDAO.delete(republicSoldier);
                }
            } else if (isClone instanceof Trooper) {
                Trooper trooper = (Trooper) isClone;
                if (trooper.getHealthPoints()<=0){
                    cloneDAO.delete(trooper);
                }
            }
        }catch (SQLException e){
            throw new SQLException("Error while attempting clone deletion ! ", e);

        }
    }


}
