package org.example.services;

import org.example.models.Jedi;
import org.example.models.Sith;
import org.example.services.characters.CloneService;
import org.example.services.characters.ForceUserService;

import java.sql.SQLException;

public class GameService {

   private ForceUserService forceUserService;
   private CloneService cloneService;

    public GameService(ForceUserService forceUserService, CloneService cloneService) {
        this.forceUserService = forceUserService;
        this.cloneService = cloneService;
    }

    public void gameInitialization() throws SQLException {

    Jedi oneRandomJedi = forceUserService.getOneRandomJedi();
    Sith oneRandomSith = forceUserService.getOneRandomSith();

    cloneService.clonesInitializer();

}
public void endGame(){}



}






