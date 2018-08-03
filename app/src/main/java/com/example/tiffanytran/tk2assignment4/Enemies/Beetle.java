package com.example.tiffanytran.tk2assignment4.Enemies;

import com.example.tiffanytran.tk2assignment4.BackgroundObjects.ImmobileObjects;
import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class Beetle extends ImmobileObjects {
    public int gameMapj;
    public int iconNum = 13;
    int gameMap[][][];
    int direction;

    public Beetle(ScreenView gameView) {
        gameMap = gameView.gameMap;
    }

    public void beetleMovement(int level) { //this function allows the beetle to move back and forth
        if (direction == 0) {
            if ((gameMap[level][j][gameMapj + 1] != 1)&&(gameMap[level][j][(gameMapj + 1)] != 7))  {
                i++;
                gameMapj++;
            } else {
                direction = 1;
            }
        }

        if (direction == 1) {
            if (gameMapj - 1 > 0) {
                if ((gameMap[level][j][gameMapj - 1] != 1)&&(gameMap[level][j][(gameMapj - 1)] != 7)) {
                    i--;
                    gameMapj--;
                } else {
                    direction = 0;
                }
            } else {
                direction = 0;
            }
        }

    }
}
