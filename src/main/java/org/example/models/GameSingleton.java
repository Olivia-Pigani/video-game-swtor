package org.example.models;

public class GameSingleton {

    private static volatile GameSingleton gameSingleton = null;

    private GameSingleton() {
    }

    ;

    public static GameSingleton getInstance() {
        if (gameSingleton == null) {
            synchronized (GameSingleton.class) {
                if (gameSingleton == null) {
                    gameSingleton = new GameSingleton();
                }
            }
        } else {
            System.out.println(" The game has been already instanciated ! ");

        }
            return gameSingleton;
    }

}
