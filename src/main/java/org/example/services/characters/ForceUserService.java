package org.example.services.characters;

import org.example.dao.JediDAO;
import org.example.dao.PowerDAO;
import org.example.dao.SithDAO;
import org.example.models.Jedi;
import org.example.models.Power;
import org.example.models.Sith;
import org.example.models.Team;

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



    // get 1 sith and put 5 random list of power
    public Sith getOneRandomSith () throws SQLException{
        List<Power> sithPowers = powerDAO.getAllPowersByTeam("SITH");
        Sith randomSith = sithDAO.getOneRandomSith();
        randomSith.setPowers(sithPowers);
        return randomSith;

    }
    public Jedi getOneRandomJedi() throws SQLException{
        List<Power> jediPowers = powerDAO.getAllPowersByTeam("REPUBLIC");
        Jedi randomJedi =  jediDAO.getOneRandomJedi();
        randomJedi.setPowers(jediPowers);
        return randomJedi;
    }


}
