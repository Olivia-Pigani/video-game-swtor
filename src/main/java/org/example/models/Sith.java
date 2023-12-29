package org.example.models;

import lombok.Data;
import org.example.models.factories.ForceUser;

import java.util.List;

@Data
public class Sith implements ForceUser {

    private int id;
    String name;
    private boolean light_saber;
    private int healthPoints;
    private final boolean hasForce = true;
    private final Team team = Team.SITH;
    private List<Power> powers;

    public Sith() {
    }

    public Sith(Builder builder){
        this.name=builder.name;
        this.light_saber=builder.light_saber;
        this.powers=builder.powers;
        this.healthPoints=builder.healthPoints;
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
