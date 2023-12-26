package org.example.models;

import lombok.Data;

@Data
public class Equipment {

    private int id;
    String name;
    private int attackPoints;
    private int healthRestorer;

    StuffType stuffType;


    public Equipment(int id, String name, int attackPoints, int healthRestorer, StuffType stuffType) {
        this.id = id;
        this.name = name;
        this.attackPoints = attackPoints;
        this.healthRestorer = healthRestorer;
        this.stuffType = stuffType;
    }
}
