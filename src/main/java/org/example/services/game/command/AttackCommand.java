package org.example.services.game.command;

import org.example.models.factories.ForceUser;
import org.example.models.factories.NonForceUser;

public class AttackCommand implements Command{

    private ForceUser forceUser;
    private NonForceUser nonForceUser;


    public AttackCommand(ForceUser forceUser) {
        this.forceUser = forceUser;
    }

    public AttackCommand(NonForceUser nonForceUser) {
        this.nonForceUser = nonForceUser;
    }

    @Override
    public void execute() {
        if (forceUser != null){
            forceUser.attack();
        } else if (nonForceUser != null ) {
            nonForceUser.attack();

        }
    }
}
