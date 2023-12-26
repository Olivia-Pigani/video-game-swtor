package org.example.models;

import lombok.Data;
import org.example.models.factories.abstractfactory.NonForceUser;

import java.util.List;

@Data
public class Trooper implements NonForceUser {


    private int id;
    private int healthPoints;
    List<Equipment> equipments;
    private final Team team = Team.SITH;

    public Trooper(int id, int healthPoints, List<Equipment> equipments) {
        this.id = id;
        this.healthPoints = healthPoints;
        this.equipments = equipments;
    }
}
