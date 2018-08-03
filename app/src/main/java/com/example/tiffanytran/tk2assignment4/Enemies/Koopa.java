package com.example.tiffanytran.tk2assignment4.Enemies;

import com.example.tiffanytran.tk2assignment4.BackgroundObjects.ImmobileObjects;
import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class Koopa extends ImmobileObjects{
    public int iconNum = 12;
    public int gameMapj;
    public int gameMap[][][];
    int direction = 0; //looking to the right

    public Koopa(ScreenView gameView){
        gameMap = gameView.gameMap;
    }

    public void koopaMovement(int level) {

        if(direction == 0 ){ //this is for the right direction
            if ((gameMap[level][j + 1][gameMapj + 1] == 1) || (gameMap[level][j + 1][gameMapj + 1] == 2)) {
                i++;
                gameMapj++;
            }
            else{
                direction = 1;
                iconNum = 14;
            }
        }

        if (direction == 1) { //this is for the left direction
            if ((gameMap[level][j + 1][gameMapj - 1] == 1) || (gameMap[level][j + 1][gameMapj - 1] == 2)) {
                i--;
                gameMapj--;
            }
            else{
                direction = 0;
                iconNum = 12;
            }
        }
    }
}
