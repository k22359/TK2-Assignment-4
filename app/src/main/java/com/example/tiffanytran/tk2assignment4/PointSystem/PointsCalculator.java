package com.example.tiffanytran.tk2assignment4.PointSystem;

/**
 * Created by tiffanytran on 5/24/17.
 */

public class PointsCalculator {
    public int points;

    PointsCalculator(){
        points = 0;
    }

    public int addPoints(GameItems gameItem){
        SuperMarioVisitor visitor = new SuperMarioVisitorImpl();
        points += gameItem.getPoints(visitor);
        return points;
    }
}
