package org.example.services.characters;

import org.example.dao.JediDAO;
import org.example.dao.PowerDAO;
import org.example.dao.SithDAO;
import org.example.models.Jedi;
import org.example.models.Power;
import org.example.models.Sith;
import org.example.models.Team;
import org.example.models.factories.ForceUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForceUserService {

    private JediDAO jediDAO;
    private SithDAO sithDAO;
    private PowerDAO powerDAO;


    public ForceUserService(JediDAO jediDAO, SithDAO sithDAO, PowerDAO powerDAO) {
        this.jediDAO = jediDAO;
        this.sithDAO = sithDAO;
        this.powerDAO = powerDAO;
    }

    public ForceUser makeAForceUser(int newCharacTeam, Boolean hasLightSaber, String name) {
        try {


        if (newCharacTeam == 1) {

            Sith.Builder builderSith = new Sith.Builder();
            if (hasLightSaber != null) {
                builderSith.light_saber(hasLightSaber);
            }
            if (name != null && !name.isEmpty()) {
                builderSith.name(name);
            }
            return builderSith.build();

        } else if (newCharacTeam == 2) {

            Jedi.Builder builderJedi = new Jedi.Builder();
            if (hasLightSaber != null) {
                builderJedi.light_saber(hasLightSaber);
            }
            if (name != null && !name.isEmpty()) {
                builderJedi.name(name);
            }
            return builderJedi.build();


        }else {

        System.out.println(" Must must select the right team ! ");
        return null;}
    }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    public ForceUser updateForceUserCharacter(int forceUserId, ForceUser forceUser) throws SQLException{
        ForceUser forceUserToUpdate = findForceUserById(forceUserId);
        if (forceUserToUpdate != null){
            if (forceUserToUpdate instanceof Sith && forceUser instanceof Sith){

                Sith updatedSith = sithDAO.update((Sith) forceUser);
                return updatedSith;
            } else if (forceUserToUpdate instanceof Jedi && forceUser instanceof  Jedi ) {

                Jedi updatedJedi = jediDAO.update((Jedi) forceUser);
                return updatedJedi;

            }
        }
        System.out.println("Something wrong ! ");
        return null;
    }



    // get 1 sith and put 5 random list of power
    public Sith getOneRandomSith() throws SQLException {
        List<Power> sithPowers = powerDAO.getAllPowersByTeam("SITH");
        Sith randomSith = sithDAO.getOneRandomSith();
        randomSith.setPowers(sithPowers);
        return randomSith;

    }

    public Jedi getOneRandomJedi() throws SQLException {
        List<Power> jediPowers = powerDAO.getAllPowersByTeam("REPUBLIC");
        Jedi randomJedi = jediDAO.getOneRandomJedi();
        randomJedi.setPowers(jediPowers);
        return randomJedi;
    }

    public List<List<ForceUser>> getAllForceUser() throws SQLException {
        List<List<ForceUser>> forceUserList = new ArrayList<>();
        List<? extends ForceUser> siths = sithDAO.getAll();
        List<? extends ForceUser> jedis = jediDAO.getAll();
        forceUserList.add((List<ForceUser>) siths);
        forceUserList.add((List<ForceUser>) jedis);

        return forceUserList;
    }

    public ForceUser findForceUserById(int forceUserId) throws SQLException {
        ForceUser sithToFind = sithDAO.getById(forceUserId);
        ForceUser jediToFind = jediDAO.getById(forceUserId);

        if (sithToFind != null){
            System.out.println("There is a sith with this id ! ");
            return sithToFind;
        } else if (jediToFind != null ) {
            System.out.println("There is a jedi with this id ! ");
            return jediToFind;
        }
        System.out.println("There is no character with such id ! ");
        return null;

    }

    public void deleteForceUser(int id) throws  SQLException{
        ForceUser forceUser = findForceUserById(id);
        if (forceUser != null){
            if (forceUser instanceof Sith){
                sithDAO.delete(id);
            } else if (forceUser instanceof Jedi) {
                jediDAO.delete(id);
            }
        }else {
            System.out.println("Character not found ! ");
        }
    }



}
