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
            if (name != null) {
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


}
