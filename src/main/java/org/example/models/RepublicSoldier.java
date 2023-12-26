package org.example.models;

import lombok.Data;

import java.util.List;

@Data
public class RepublicSoldier {


    private int id;
    private int healthPoints;
    List<Equipment> equipments;
    private Team team = Team.REPUBLIC;

    public RepublicSoldier(int id, int healthPoints, List<Equipment> equipments, Team team) {
        this.id = id;
        this.healthPoints = healthPoints;
        this.equipments = equipments;
        this.team = team;
    }
}
