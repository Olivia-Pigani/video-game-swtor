package org.example;

import org.example.models.GameSingleton;

public class Main {
    public static void main(String[] args) {

        GameSingleton game = GameSingleton.getInstance();
//        GameSingleton game1 = GameSingleton.getInstance();

//        System.out.println(game1);
        System.out.println(game);


    }
}