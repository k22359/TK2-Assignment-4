package com.example.tiffanytran.tk2assignment4.Mario;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class FireMario extends Mario {
    int gameMap[][][];
    public int shoot = 0;
    public int attackiconNum = 8; //for the fireball
    public int lives = 1;

    public FireMario(ScreenView gameView) {
        this.gameMap = gameView.gameMap;
    }

}
