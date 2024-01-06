package org.example.services.game.command;

import lombok.Data;
import org.example.services.game.composite.GameComponent;

@Data
public class HealCommand implements Command{

    private GameComponent healer;
    private int heal_points;


    @Override
    public void execute(GameComponent teamPlayer) {
        // get random heal on itself

    }
}
