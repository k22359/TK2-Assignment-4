package com.example.tiffanytran.tk2assignment4.Mario;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class SuperMario extends Mario {
    int gameMap[][][];
    public int lives = 1; //will have one life since it's a transformation from little Mario
    public SuperMario(ScreenView gameView) {
        this.gameMap = gameView.gameMap;

    }


}