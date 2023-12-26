package org.example.models;

import lombok.Data;

import java.util.List;

@Data
public class Sith {

    private int id;
    private boolean light_saber;
    private List<Power> powers;
    private int healthPoints;
    private final Team team = Team.SITH;


    public Sith(int id, boolean light_saber, List<Power> powers, int healthPoints) {
        this.id = id;
        this.light_saber = light_saber;
        this.powers = powers;
        this.healthPoints = healthPoints;
    }
}
