package org.example.services.game.command;

import org.example.services.game.composite.GameComponent;
import org.example.services.game.composite.TeamComposite;

public interface Command {
    void execute(GameComponent teamPlayer, TeamComposite teamSith, TeamComposite teamRepublic);
}
