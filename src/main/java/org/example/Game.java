package org.example;

import org.example.models.Team;
import org.example.services.game.GameService;
import org.example.services.game.composite.TeamComposite;

import java.sql.SQLException;

public class Game {

    private static volatile Game gameInstance = null;
    private GameService gameService;

    private Game() {

    }

    public static Game getInstance() {
        if (gameInstance == null) {
            synchronized (Game.class) {
                if (gameInstance == null) {
                    gameInstance = new Game();
                }
            }
        } else {
            System.out.println(" The game has been already instanciated ! ");

        }
        return gameInstance;
    }


    public void playGame(Team chosenTeam) throws SQLException {
        gameService.TeamsInitialization();

        while (gameService.getTeamSith().isAlive() && gameService.getTeamRepublic().isAlive()){
            if (chosenTeam == Team.SITH) {
                gameService.turnInsideTeam(gameService.getTeamSith(), chosenTeam);
                gameService.turnInsideTeam(gameService.getTeamRepublic(), Team.REPUBLIC);
            } else {
                gameService.turnInsideTeam(gameService.getTeamRepublic(), chosenTeam);
                gameService.turnInsideTeam(gameService.getTeamSith(), Team.SITH);
            }


        }



        gameService.endGame();
    }



    ;


}
