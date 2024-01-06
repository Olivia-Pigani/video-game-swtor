package org.example.services.game.composite;

import lombok.Data;
import org.example.models.Team;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamComposite implements GameComponent{

    private List<GameComponent> teamMembers = new ArrayList<>();

    public void addAMember(GameComponent member){
        teamMembers.add(member);
    }
    public void addAMember(List<? extends GameComponent> members){
        for (GameComponent member:members
             ) {
            teamMembers.add(member);
        }
    }


    public Team getTeamName(TeamComposite teamName){
        if (teamName.equals("SITH")){
            return Team.SITH;
        } else if (teamName.equals("REPUBLIC")) {
            return Team.REPUBLIC;
        }
        System.out.println("There is no such team name ! ");
        return null;
    }
    @Override
    public boolean isAlive() {
        return teamMembers.stream().anyMatch(GameComponent::isAlive);
    }
}

