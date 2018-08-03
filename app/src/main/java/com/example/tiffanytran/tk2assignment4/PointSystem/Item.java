package com.example.tiffanytran.tk2assignment4.PointSystem;

/**
 * Created by tiffanytran on 5/24/17.
 */

public class Item implements GameItems {
    public int i, j;
    public int exists = 0;
    public int value;

    public int getValue(){
        return value;
    }

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }

    public void createObject(int columnSize, int rowSize, int colNum, int rowNum){
        i = rowNum;
        j = colNum;
        exists = 1;
    }
}
