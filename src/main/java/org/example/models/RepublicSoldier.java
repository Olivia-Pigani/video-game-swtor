package org.example.models;

import lombok.Data;
import org.example.models.factories.NonForceUser;
import org.example.services.game.states.AliveState;
import org.example.services.game.states.CharacterState;

import java.util.List;

@Data
public class RepublicSoldier implements IsClone, NonForceUser, Cloneable {


    private int id;
    final String name = "republic soldier";
    final boolean lightSaber = false;
    private int healthPoints = 100;
    private final boolean hasForce = false;
    private Team team = Team.REPUBLIC;
    List<Equipment> equipments;




    public RepublicSoldier() {
    }

    // State
    @Override
    public void changeState(CharacterState changeState) {

    }

    @Override
    public void doThings() {

    }

    @Override
    public void updateHealthPoints(int healthChange) {

    }

    // Prototype
    @Override
    public NonForceUser clone() {
        try {
            return (RepublicSoldier) super.clone();
        }catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
