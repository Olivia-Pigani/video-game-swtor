package org.example.services.game.command;


import lombok.Data;
import org.example.services.game.composite.GameComponent;

@Data
public class AttackCommand implements Command{
    private GameComponent attacker;
    private GameComponent opponent;
    private int attack_points;


    @Override
    public void execute(GameComponent teamPlayer) {
        // strategy give us attacker, must choose randomly opponent
        // get attacker random attack
    }




}
