package org.example;

import org.example.services.game.GameService;

public class Game {

    private static volatile Game gameInstance = null;
    private GameService gameService;

    private Game() {
    };

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



    public void startGame(){

    }

    public void endGame(){

    };





}
