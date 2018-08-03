package com.example.tiffanytran.tk2assignment4.BackgroundObjects;

/**
 * Created by tiffanytran on 5/31/17.
 */

public class greenPipe extends ImmobileObjects {
    public int iconNum = 6;
    public int monIconNum = 10;
    int count = 0;
    public int monsteri, monsterj, monsterExists = 0;

    public void Monster(){ //makes the plant monster appear and disappear
        monsteri = i;
        monsterj = j-1;

        if(count == 5){
            monsterExists = 1;
            count++;
        }else{
            count++;
            if(count>10) {
                count = 0;
                monsterExists = 0;
            }
        }
    }
}
