package org.example.services.characters;

import org.example.dao.CloneDAO;
import org.example.dao.EquipmentDAO;
import org.example.models.*;

import java.sql.SQLException;
import java.util.List;

public class CloneService {

    private CloneDAO cloneDAO;
    private EquipmentDAO equipmentDAO;


    public CloneService(CloneDAO cloneDAO, EquipmentDAO equipmentDAO) {
        this.cloneDAO = cloneDAO;
        this.equipmentDAO = equipmentDAO;
    }

    private void produceClones(Team team, int nbOfClone, List<Equipment> equipments) throws SQLException {

        for (int i = 0; i < nbOfClone; i++) {
            if (team == Team.REPUBLIC) {
                RepublicSoldier republicSoldier = new RepublicSoldier();
                republicSoldier.setEquipments(equipments);
                cloneDAO.save(republicSoldier);
            } else if (team == Team.SITH) {
                Trooper trooper = new Trooper();
                trooper.setEquipments(equipments);
                cloneDAO.save(trooper);
            } else {
                throw new SQLException(" incorrect team name ! ");
            }
        }
    }

    public void clonesInitializer() throws SQLException {


        List<Equipment> randomizedEquipmentSet = equipmentDAO.getRandomSet();

        produceClones(Team.REPUBLIC, 3, randomizedEquipmentSet);
        produceClones(Team.SITH, 3, randomizedEquipmentSet);

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
