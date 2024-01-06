package org.example.services.game.command;

import org.example.services.game.composite.GameComponent;

public interface Command {
    void execute(GameComponent teamPlayer);
}
