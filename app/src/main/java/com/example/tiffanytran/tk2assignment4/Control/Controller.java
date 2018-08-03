package com.example.tiffanytran.tk2assignment4.Control;

import com.example.tiffanytran.tk2assignment4.BackgroundObjects.Brick;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.NoHitBrick;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.Numbers;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.greenPipe;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.itemBrick;
import com.example.tiffanytran.tk2assignment4.Enemies.Beetle;
import com.example.tiffanytran.tk2assignment4.Enemies.Koopa;
import com.example.tiffanytran.tk2assignment4.Items.Coin;
import com.example.tiffanytran.tk2assignment4.Items.Flower;
import com.example.tiffanytran.tk2assignment4.Items.Mushroom;
import com.example.tiffanytran.tk2assignment4.Mario.FireMario;
import com.example.tiffanytran.tk2assignment4.Mario.SuperMario;
import com.example.tiffanytran.tk2assignment4.Mario.regMario;
import com.example.tiffanytran.tk2assignment4.PointSystem.PointsCalculator;
import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class Controller {

    private GameThread gameThread;
    ScreenView threadgameView;

    int gameMap[][][];
    int gameMap2[][];

    Numbers num =  new Numbers();
    public PointsCalculator poi;
    NoHitBrick noh[] = new NoHitBrick[200];
    Brick bri[] = new Brick[200];
    greenPipe gre[] = new greenPipe[200];
    itemBrick ite[] = new itemBrick[200];
    Coin coi[] = new Coin[200];
    Flower flo[] = new Flower[200];
    Mushroom mus[] = new Mushroom[200];
    Koopa koopa[] = new Koopa[20];
    Beetle beetle[] = new Beetle[20];
    regMario littleMario;
    SuperMario superMario;
    FireMario fireMario;

    public Controller(ScreenView gameView) {
        gameThread = new GameThread(this, gameView);
        threadgameView = gameView;
        littleMario = gameView.littleMario;
        superMario = gameView.superMario;
        fireMario = gameView.fireMario;
        noh = gameView.noh;
        num = gameView.num;
        bri = gameView.bri;
        gre = gameView.gre;
        ite = gameView.ite;
        coi = gameView.coi;
        flo = gameView.flo;
        mus = gameView.mus;
        beetle = gameView.beetle;
        koopa = gameView.koopa;
        poi = gameView.poi;

        gameMap = gameView.gameMap;
        gameMap2 = gameView.gameMap2;

    }
    public void startGameThread(){
        gameThread.start();
    }

    public void update() {
        System.out.println("Current mode is "+ num.marioNum);
        littleMario.movement();
        superMario.movement();
        fireMario.movement();


        if (num.delay > 2) {
            num.delay =0;

            littleMario.jump(num.level, num.marioNum); //checks if mario should jump
            littleMario.gravity(num.level, num.currentColumn); //checks if mario should fall
            if(littleMario.bricktype > 0){  //if mario hits a brick, bricktype will be a number greater than 0
                if(littleMario.bricktype == 2){  //Mario hits a brick that can disappear
                    for (int j = 0; j < num.briNum; j++) {
                        if (bri[j].exists == 1) {
                            if (bri[j].i == littleMario.bricki) {
                                if (bri[j].j == littleMario.brickj) {
                                    bri[j].exists = 0; //finds the right brick and makes it disappear
                                }
                            }
                        }
                    }

                }
                if(littleMario.bricktype == 3){ //Mario hits a item brick
                    for (int j = 0; j < num.iteNum; j++) {
                        if (ite[j].exists == 1) {
                            if (ite[j].i == littleMario.bricki) {
                                if (ite[j].j == littleMario.brickj) {
                                    ite[j].exists = 0;  //makes the brick disappear
                                    if(num.marioNum == 0){
                                        gameMap[num.level][ite[j].j+1][ite[j].gameMapj-1] = 6;  //If mario is a small mario, it will drop a mushroom and he will become a super mario
                                        threadgameView.createObject(6, ite[j].j, ite[j].i);
                                        num.marioNum = 1;
                                    }
                                    else if(num.marioNum == 1){
                                        gameMap[num.level][ite[j].j+1][ite[j].gameMapj-1] = 5;  //If mario is a super mario, it will drop a mushroom and he will become a fire mario

                                        threadgameView.createObject(5, ite[j].j, ite[j].i);
                                        num.marioNum = 2;
                                        checkOverlap(5);
                                    }
                                }
                            }
                        }
                    }
                }
                littleMario.bricktype = 0;
            }

            updateBackground();

            if (fireMario.shoot == 1) {  //increments the counter for fireball location
                num.fireCounter++;
                if (num.fireCounter >= 9) {
                    fireMario.shoot = 0;
                    num.fireCounter = 0;
                }
            }

            if(littleMario.collision == 1){ //lets mario drop if he collides into a wall
                littleMario.collision = 0;
                littleMario.j++;

            }
            monstersOverlap();

        } else {
            num.delay++;
        }

        for (int j = 0; j < num.greNum; j++) { //animates the plant monster
            if (gre[j].exists == 1) {
                gre[j].Monster();
            }
        }
        marioLives();
    }

    public void updateBackground(){


        if((littleMario.gameMapj>0)&&(num.level < 3)) {
            if ((gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)] == 0) || ((gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)] > 3)&&(gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)] != 7)) ) { //will move forward if the spot in front of mario is not blocked.
                if ((gameMap[num.level][littleMario.j - 1][(littleMario.gameMapj - 1)] == 0) || (gameMap[num.level][littleMario.j - 1][(littleMario.gameMapj - 1)] > 3) || (num.marioNum == 0)) { //can not move forward if the top part of super/fire mario is blocked
                    if ((gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)] > 3) || (gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)] < 7)) {  //sees if mario will overlap with an item(coin, mushroom, or flower)
                        checkOverlap(gameMap[num.level][littleMario.j][(littleMario.gameMapj - 1)]);
                    }

                    if( (num.marioNum == 1)||(num.marioNum == 2)){  //checks if the top part of super/fire mario overlaps with an item
                        checkOverlap(gameMap[num.level][littleMario.j-1][(littleMario.gameMapj-1)]);
                    }

                    for (int j = 0; j < num.nohNum; j++) {  //updates the background layout by moving everything one space right if mario also moves
                        if (noh[j].exists == 1) {
                            if (noh[j].i == 16) {
                                noh[j].exists = 0;
                            } else {
                                noh[j].i++;

                            }
                        }
                    }

                    for (int j = 0; j < num.briNum; j++) {
                        if (bri[j].exists == 1) {
                            if (bri[j].i == 16) {
                                bri[j].exists = 0;
                            } else {
                                bri[j].i++;
                            }
                        }
                    }


                    for (int j = 0; j < num.iteNum; j++) {
                        if (ite[j].exists == 1) {
                            if (ite[j].i == 16) {
                                ite[j].exists = 0;
                            } else {
                                ite[j].i++;
                            }
                        }
                    }


                    for (int j = 0; j < num.coiNum; j++) {
                        if (coi[j].exists == 1) {
                            if (coi[j].i == 16) {
                                coi[j].exists = 0;
                            } else {
                                coi[j].i++;
                            }
                        }
                    }


                    for (int j = 0; j < num.floNum; j++) {
                        if (flo[j].exists == 1) {
                            if (flo[j].i == 16) {
                                flo[j].exists = 0;
                            } else {
                                flo[j].i++;
                            }
                        }
                    }


                    for (int j = 0; j < num.musNum; j++) {
                        if (mus[j].exists == 1) {
                            if (mus[j].i == 16) {
                                mus[j].exists = 0;
                            } else {
                                mus[j].i++;
                                                         }
                        }
                    }


                    for (int j = 0; j < num.greNum; j++) {
                        if (gre[j].exists == 1) {
                            if (gre[j].i == 16) {
                                gre[j].exists = 0;
                            } else {
                                gre[j].i++;
                            }
                        }
                    }

                    for (int j = 0; j < num.koopaNum; j++) {
                        if (koopa[j].exists == 1) {
                            if (koopa[j].i == 16) {
                                koopa[j].exists = 0;
                            } else {
                                koopa[j].i++;
                                koopa[j].koopaMovement(num.level);
                            }
                        }
                    }

                    for (int j = 0; j < num.beetleNum; j++) {
                        if (beetle[j].exists == 1) {
                            if (beetle[j].i == 16) {
                                beetle[j].exists = 0;
                            } else {
                                beetle[j].i++;
                                beetle[j].beetleMovement(num.level);
                            }
                        }
                    }

                    if (num.currentColumn < 68) {
                        for (int a = 0; a < 9; a++) {
                            threadgameView.createObject(gameMap[num.level][a][67 - num.currentColumn], a, 0);
                        }
                    } else if(num.columncurrent2 < 7){  //uses the next level to update the layout
                        if(num.level < 2) {
                            for (int a = 0; a < 9; a++) {
                                threadgameView.createObject(gameMap[num.level + 1][a][67 - num.columncurrent2], a, 0);
                            }
                        }
                            num.columncurrent2++;


                    }else{
                        if(num.level < 2) {  //switches to the next level
                            for (int a = 0; a < 9; a++) {
                                threadgameView.createObject(gameMap[num.level + 1][a][67 - num.columncurrent2], a, 0);
                            }
                        }
                        num.level++;
                        num.columncurrent2 = 0;
                        num.currentColumn = 8;
                        littleMario.gameMapj = 68;
                    }
                    num.currentColumn++;
                    littleMario.gameMapj--;
                }
            }

        }

    }

    public void checkOverlap(int input){  //check if an item overlaps with mario

        int mode = num.marioNum;

        if((mode == 1)||(mode == 2)){
            mode = 1;
        }

        if (input == 4) { //checks coins
            for (int j = 0; j < num.coiNum; j++) {
                if (coi[j].exists == 1) {
                    if (coi[j].i == littleMario.i-1) {
                        if(coi[j].j == littleMario.j) {
                            coi[j].exists = 0;
                            poi.addPoints(coi[j]);

                        }
                        if(coi[j].j == littleMario.j-mode) {
                            coi[j].exists = 0;
                            poi.addPoints(coi[j]);

                        }
                    }
                }
            }
        } else if (input == 5) { //checks flowers
            for (int j = 0; j < num.floNum; j++) {
                if (flo[j].exists == 1) {
                    if (flo[j].i == littleMario.i-1) {
                        if(flo[j].j == littleMario.j) {
                            flo[j].exists = 0;
                            poi.addPoints(flo[j]);
                            num.marioNum = 2;

                        }
                        if(flo[j].j == littleMario.j-mode) {
                            flo[j].exists = 0;
                            poi.addPoints(flo[j]);
                            num.marioNum = 2;

                        }
                    }
                }
            }
        } else if (input == 6) {  //checks mushrooms
            for (int j = 0; j < num.musNum; j++) {
                if (mus[j].exists == 1) {
                    if (mus[j].i == littleMario.i-1) {
                        if(mus[j].j == littleMario.j) {
                            mus[j].exists = 0;
                            poi.addPoints(mus[j]);
                            if(num.marioNum == 0){
                            num.marioNum = 1;
                            }

                        }
                        if(mus[j].j == littleMario.j-mode) {
                            mus[j].exists = 0;
                            poi.addPoints(mus[j]);
                            if(num.marioNum == 0){
                                num.marioNum = 1;
                            }

                        }
                    }
                }
            }
        }
    }

    public void monstersOverlap(){  //checks if mario overlaps with monster. If so, loses life.
        for (int j = 0; j < num.greNum; j++) {
            if (gre[j].exists == 1) {
                if(gre[j].i == littleMario.i){
                    if(gre[j].j-1 == littleMario.j){
                        if(gre[j].monsterExists == 1) {
                            marioDied(num.marioNum);
                        }
                    }
                }

            }
        }

        for (int j = 0; j < num.koopaNum; j++) {
            if (koopa[j].exists == 1) {
                if(koopa[j].i == littleMario.i){
                    if(koopa[j].j == littleMario.j){
                        marioDied(num.marioNum);
                    }
                }
            }
        }

        for (int j = 0; j < num.beetleNum; j++) {
            if (beetle[j].exists == 1) {
                if(beetle[j].i == littleMario.i){
                    if(beetle[j].j == littleMario.j){
                        marioDied(num.marioNum);
                    }
                }
            }
        }
    }

    public void marioLives(){  //updates how many lives mario has. Affects transformation if dies.
        if(num.marioNum == 0){
            if(littleMario.lives < 0){
                //end game
            }
        }
        if(num.marioNum == 1){
            if(superMario.lives < 0){
                num.marioNum = 0;
                superMario.lives = 1;
            }
        }
        if(num.marioNum == 2){
            if(fireMario.lives < 0){
                num.marioNum = 0;
                fireMario.lives = 1;
            }
        }
    }

    public void marioDied(int input){  //what happens when he dies
        if(input == 0){
            littleMario.lives--;
        }
        if(input == 1){
            superMario.lives--;
        }
        if(input == 2){
            fireMario.lives--;
        }
    }
}
