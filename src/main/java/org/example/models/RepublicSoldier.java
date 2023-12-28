package org.example.models;

import lombok.Data;
import org.example.models.factories.NonForceUser;

import java.util.List;

@Data
public class RepublicSoldier implements NonForceUser {


    private int id;
    final String name = null;
    private int healthPoints;
    List<Equipment> equipments;
    private Team team = Team.REPUBLIC;

    public RepublicSoldier(int id,  int healthPoints, List<Equipment> equipments, Team team) {
        this.id = id;
        this.healthPoints = healthPoints;
        this.equipments = equipments;
        this.team = team;
    }
}
