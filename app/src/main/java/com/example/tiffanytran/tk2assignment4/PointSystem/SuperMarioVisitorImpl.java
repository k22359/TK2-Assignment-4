package com.example.tiffanytran.tk2assignment4.PointSystem;

/**
 * Created by tiffanytran on 5/24/17.
 */

public class SuperMarioVisitorImpl implements SuperMarioVisitor {
    @Override
    public int visit(Enemy enemy){
        return enemy.getReward();
    }
    @Override
    public int visit(Item item){
        return item.getValue();
    }
}
