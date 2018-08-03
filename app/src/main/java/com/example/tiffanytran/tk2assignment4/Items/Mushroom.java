package com.example.tiffanytran.tk2assignment4.Items;

import com.example.tiffanytran.tk2assignment4.PointSystem.Item;
import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class Mushroom extends Item {
    public int iconNum = 5;
    int value = 1000;
    int gameMap[][][];
    public int gameMapj;
    public int getValue(){
        return value;
    }

    public Mushroom(ScreenView gameView){
        gameMap = gameView.gameMap;
    }

}
