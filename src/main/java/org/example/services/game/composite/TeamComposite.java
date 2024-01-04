package org.example.services.game.composite;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean isAlive() {
        return teamMembers.stream().anyMatch(GameComponent::isAlive);
    }
}
