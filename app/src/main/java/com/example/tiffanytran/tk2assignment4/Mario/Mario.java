package com.example.tiffanytran.tk2assignment4.Mario;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class Mario {

    public int iconNum = 0;
    public int jump = 0;
    int jumping = 0;
    int jumpeffect = 0;
    int gravity = 1;
    int gravityeffect = 0;
    int walk = 0;
    public int i = 8,j = 7;
    public int gameMapi = 7, gameMapj = 59;
    int count = 0;


    public void movement() { //this changes the icon for Mario to make it look like he's moving
        if (iconNum == 0) {
            iconNum = 2;
        } else {
            if ((jumpeffect == 1)||(gravityeffect ==1)) {
                iconNum = 1;
            } else if (walk == 0) {
                iconNum = 2;
                walk++;
            } else if (walk == 1) {
                iconNum = 3;
                walk--;
            }
        }
    }

}