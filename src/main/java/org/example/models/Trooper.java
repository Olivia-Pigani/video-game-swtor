package org.example.models;

import lombok.Data;
import org.example.models.factories.NonForceUser;
import org.example.services.game.composite.GameComponent;
import org.example.services.game.states.AliveState;
import org.example.services.game.states.CharacterState;
import org.example.services.game.states.DeadState;

import java.util.List;

@Data
public class Trooper implements IsClone,NonForceUser, Cloneable, GameComponent {


    private int id;
    final String name = "trooper";
    final boolean lightSaber = false;
    private int healthPoints = 100;
    private final boolean hasForce = false;
    private final Team team = Team.SITH;
    List<Equipment> equipments;
    private CharacterState characterState;



    public Trooper() {
        this.characterState = new AliveState();
    }

    @Override
    public boolean isAlive() {
        return this.characterState instanceof AliveState;
    }

    // State
    @Override
    public void changeState(CharacterState changeState) {
    this.characterState=changeState;
    }

    @Override
    public void doThings() {
        this.characterState.doThings();
    }

    @Override
    public void updateHealthPoints(int healthChange) {
    this.healthPoints += healthChange;
    if (this.healthPoints<=0){
        changeState(new DeadState());
    }
    }

    // Prototype
    @Override
    public NonForceUser clone() {
        try {
         return (Trooper) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
