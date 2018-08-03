package com.example.tiffanytran.tk2assignment4.BackgroundObjects;

/**
 * Created by tiffanytran on 5/30/17.
 */

public abstract class ImmobileObjects {
    public int i,j;
    public int exists = 0;
    public int iconNum;

    public void createObject(int columnSize, int rowSize, int colNum, int rowNum){
        i = rowNum;
        j = colNum;

        exists = 1;

    }

}
