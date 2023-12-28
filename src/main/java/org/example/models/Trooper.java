package org.example.models;

import lombok.Data;
import org.example.models.factories.NonForceUser;

import java.util.List;

@Data
public class Trooper implements NonForceUser, Cloneable {


    private int id;
    final String name = "trooper";
    private int healthPoints;
    List<Equipment> equipments;
    private final Team team = Team.SITH;

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
