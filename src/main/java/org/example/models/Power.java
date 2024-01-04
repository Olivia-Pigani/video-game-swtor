package org.example.models;

import lombok.Data;

@Data
public class Power {

    private int id;
    String name;
    private int attackPoints;
    private int healthRestorer;
    Team team;

    public Power(int id, String name, int attackPoints, int healthRestorer, Team team) {
        this.id = id;
        this.name = name;
        this.attackPoints = attackPoints;
        this.healthRestorer = healthRestorer;
        this.team = team;
    }
}
