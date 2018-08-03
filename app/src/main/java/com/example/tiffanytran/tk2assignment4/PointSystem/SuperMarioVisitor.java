package com.example.tiffanytran.tk2assignment4.PointSystem;

/**
 * Created by tiffanytran on 5/24/17.
 */

public interface SuperMarioVisitor {
    int visit(Enemy enemy);
    int visit(Item item);
}
