package org.example.models.factories.abstractfactory;

import lombok.Data;
import org.example.models.Equipment;
import org.example.models.Team;

import java.util.List;

@Data
public class NonForceUserFactory implements CharactersFactory{

    private int id;
    private List<Equipment> equipments;
    private int healthPoints;
    private Team team;

    public NonForceUserFactory(int id, List<Equipment> equipments, int healthPoints, Team team) {
        this.id = id;
        this.equipments = equipments;
        this.healthPoints = healthPoints;
        this.team = team;
    }


    @Override
    public ForceUserFactory produceForceUser(String type) {
        return null;
    }

    @Override
    public NonForceUserFactory produceNonForceUser(String type) {
        return null;
    }
}
