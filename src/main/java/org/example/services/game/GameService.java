package org.example.services.game;


import org.example.models.Team;
import org.example.services.characters.CloneService;
import org.example.services.characters.ForceUserService;
import org.example.services.game.composite.TeamComposite;

import java.sql.SQLException;

public class GameService {
    private ForceUserService forceUserService;
    private CloneService cloneService;
    private TeamComposite teamSith;
    private TeamComposite teamRepublic;


    public GameService(ForceUserService forceUserService, CloneService cloneService, TeamComposite teamSith, TeamComposite teamRepublic) {
        this.forceUserService = forceUserService;
        this.cloneService = cloneService;
        this.teamSith = teamSith;
        this.teamRepublic = teamRepublic;
    }

    private void TeamsInitialization() throws SQLException {



        teamSith = new TeamComposite();
        teamSith.addAMember(forceUserService.getOneRandomSith());
        teamSith.addAMember(cloneService.produceClones(Team.SITH,3));

        teamRepublic = new TeamComposite();
        teamRepublic.addAMember(forceUserService.getOneRandomJedi());
        teamRepublic.addAMember(cloneService.produceClones(Team.REPUBLIC,3));
    }



}






