package org.example.models.factories;

import org.example.services.game.states.CharacterState;

public interface NonForceUser {
    NonForceUser clone();

    // State
    void changeState(CharacterState changeState);
    void doThings();
    void updateHealthPoints(int healthChange);
}
