package org.example;

public class Game {

    private static volatile Game gameInstance = null;

    private Game() {
    }

    ;

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







}
