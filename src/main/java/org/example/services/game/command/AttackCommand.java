package org.example.services.game.command;


import lombok.Data;
import org.example.models.*;
import org.example.models.factories.ForceUser;
import org.example.services.game.composite.GameComponent;
import org.example.services.game.composite.TeamComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class AttackCommand implements Command {
    private GameComponent attacker;
    private GameComponent opponent;
    private int attack_points;


    @Override
    public void execute(GameComponent teamPlayer, TeamComposite teamSith, TeamComposite teamRepublic) {
        Random random = new Random();
        int lightSaber = 35;
        List<GameComponent> allSithMembers = (List<GameComponent>) teamSith;
        List<GameComponent> allRepublicMembers = (List<GameComponent>) teamRepublic

        // differencier le tour de la machine et du user
        if (teamPlayer instanceof IsClone) {

            if (teamPlayer instanceof Trooper) {
                Trooper trooper = (Trooper) teamPlayer;
                List<Equipment> equipmentList = trooper.getEquipments();
                int index = random.nextInt(equipmentList.size());
                Equipment equipment = equipmentList.get(index);

                attack_points = equipment.getAttackPoints();
                this.opponent = randomOpponent(allRepublicMembers);
                // cast gamecomponent into real model
                if (this.opponent instanceof Sith){
                    // opponent is the sith in the teamSith
                }//...



                System.out.println("The trooper fight " + opponent + " with " + equipment.getName() + " -" + attack_points + " points removed ! ");

            } else {
                RepublicSoldier republicSoldier = (RepublicSoldier) teamPlayer;
                List<Equipment> equipmentList = republicSoldier.getEquipments();
                int index = random.nextInt(equipmentList.size());
                Equipment equipment = equipmentList.get(index);

                attack_points = equipment.getAttackPoints();

                // method to get the opponent
                System.out.println("The trooper fight " + opponent + " with " + equipment.getName() + " -" + attack_points + " points removed ! ");

            }

        } else if (teamPlayer instanceof ForceUser) {

            ForceUser forceUser = (ForceUser) teamPlayer;
            if (forceUser instanceof Sith) {
                Sith sith = (Sith) teamPlayer;

                List<Power> powerList = sith.getPowers();
                if (sith.isLight_saber()) {

                    int action = Math.random() < 0.2 ? 1 : 2;
                    if (action == 1) {
                        attack_points = lightSaber;
                        // method to get the opponent
                        System.out.println("The sith fight with a light_saber : - "
                                + attack_points + " points for the opponent " + opponent);
                    } else {
                        int index = random.nextInt(powerList.size());
                        Power power = powerList.get(index);
                        attack_points = power.getAttackPoints();
                        // method to get the opponent
                        System.out.println("The sith fight with a power  : " +
                                power.getName() + "  - " + attack_points +
                                " points for the opponent " + opponent);

                    }
                }
            } else {
                Jedi jedi = (Jedi) teamPlayer;

                List<Power> powerList = jedi.getPowers();
                if (jedi.isLight_saber()) {

                    int action = Math.random() < 0.2 ? 1 : 2;
                    if (action == 1) {
                        attack_points = lightSaber;
                        // method to get the opponent
                        System.out.println("The jedi fight with a light_saber : - "
                                + attack_points + " points for the opponent " + opponent);
                    } else {
                        int index = random.nextInt(powerList.size());
                        Power power = powerList.get(index);
                        attack_points = power.getAttackPoints();
                        // method to get the opponent
                        System.out.println("The jedi fight with a power  : " +
                                power.getName() + "  - " + attack_points +
                                " points for the opponent " + opponent);

                    }
                }


            }
        }


    }
    public GameComponent randomOpponent (List < GameComponent > opponentMembers) {
        Random random = new Random();
        int index = random.nextInt(opponentMembers.size());
        return opponentMembers.get(index);

    }



}
