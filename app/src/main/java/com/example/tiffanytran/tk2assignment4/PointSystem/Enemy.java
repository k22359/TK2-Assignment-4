package com.example.tiffanytran.tk2assignment4.PointSystem;

/**
 * Created by tiffanytran on 5/24/17.
 */

public abstract class Enemy implements GameItems {
    int reward;

    public int getReward(){
        return reward;
    }

    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }
}
