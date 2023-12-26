package org.example.models;

import lombok.Data;
import org.example.models.factories.abstractfactory.ForceUser;

import java.util.List;

@Data
public class Jedi implements ForceUser {

    private int id;
    private boolean light_saber;
    private List<Power> powers;
    private int healthPoints;
    private final Team team = Team.REPUBLIC;


    public Jedi(int id, boolean light_saber, List<Power> powers, int healthPoints) {
        this.id = id;
        this.light_saber = light_saber;
        this.powers = powers;
        this.healthPoints = healthPoints;
    }
}
