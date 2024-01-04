package org.example.models;

import lombok.Data;
import org.example.models.factories.ForceUser;
import org.example.services.game.composite.GameComponent;
import org.example.services.game.states.AliveState;
import org.example.services.game.states.CharacterState;
import org.example.services.game.states.DeadState;

import java.util.List;

@Data
public class Sith implements ForceUser, GameComponent {

    private int id;
    String name = "Sith master";
    private boolean light_saber;
    private int healthPoints = 100;
    private final Boolean hasForce = true;
    private final Team team = Team.SITH;
    private List<Power> powers;
    private CharacterState characterState;


    public Sith() {
        this.characterState = new AliveState();
    }

    public Sith(Builder builder){
        this.name=builder.name;
        this.light_saber=builder.light_saber;
        this.powers=builder.powers;
        this.healthPoints=builder.healthPoints;
    }

    @Override
    public boolean isAlive() {
        return this.characterState instanceof AliveState;
    }

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

    public static class Builder {

        String name;
        private boolean light_saber;
        private List<Power> powers;
        private int healthPoints;
        private final Team team = Team.SITH;

        public Builder name (String name){
            this.name=name;
            return this;
        }
        public Builder powers (List<Power> powers){
            this.powers=powers;
            return this;
        }
        public Builder light_saber (boolean light_saber){
            this.light_saber=light_saber;
            return this;
        }
        public Builder healthPoints (int healthPoints){
            this.healthPoints=healthPoints;
            return this;
        }

       public Sith build(){
            return new Sith(this);
       }






    }

}
