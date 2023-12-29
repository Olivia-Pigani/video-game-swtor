package org.example.models;

import lombok.Data;
import org.example.models.factories.NonForceUser;

import java.util.List;

@Data
public class Trooper implements IsClone,NonForceUser, Cloneable {


    private int id;
    final String name = "trooper";
    final boolean lightSaber = false;
    private int healthPoints = 100;
    private final boolean hasForce = false;
    private final Team team = Team.SITH;
    List<Equipment> equipments;

    public Trooper() {
    }

    @Override
    public NonForceUser clone() {
        try {
         return (Trooper) super.clone();
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
