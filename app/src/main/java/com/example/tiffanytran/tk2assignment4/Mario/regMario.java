package com.example.tiffanytran.tk2assignment4.Mario;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/31/17.
 */

public class regMario extends Mario {
    int gameMap[][][];
    public int lives = 3;
    public int collision = 0;
    public int height = 1;
    public int bricktype, bricki, brickj;
    public regMario(ScreenView gameView) {
        this.gameMap = gameView.gameMap;

    }

    public void jump(int level, int mode) {

       if(mode == 0){
           mode = 1;
       }
        else if(mode == 1){
            mode = 2;
        }


        if ((jump == 1) && (jumping == 0)) {
            gravity = 0;
            if (count == 2) {
                jump = 0;
                count = 0;
                jumping = 1;
                gravity = 1;
                jumpeffect = 0;
            } else {
                count++;
                if ((gameMap[level][j-1][gameMapj] == 0)||(gameMap[level][j-1][gameMapj] >3)) {
                    if(j-mode>-1) {
                        if ((gameMap[level][j - mode][gameMapj] == 0) || (gameMap[level][j - mode][gameMapj] > 3)) {
                            System.out.println(gameMap[level][j - mode][gameMapj] + "MODE IS" + mode);
                            if (j > 1) {
                                jumpeffect = 1;
                                j--;
                            }
                        }else{
                            System.out.println("UJHNDNJSKNDKJSNKJSD");
                            if ((gameMap[level][j-mode][gameMapj] == 2)) {
                                System.out.println("RECOGNIZES BRICK BRICK"+j+brickj);
                                gameMap[level][j-mode][gameMapj] = 0;
                                bricktype = 2;
                                bricki = i;
                                brickj = j-mode;
                            }
                            if ((gameMap[level][j-mode][gameMapj] == 3)) {
                                gameMap[level][j-mode][gameMapj] = 0;
                                bricktype = 3;
                                bricki = i;
                                brickj = j-mode;
                            }
                            jump = 0;
                            count = 0;
                            gravity = 1;
                            jumpeffect = 0;
                        }
                    }
                }
                else{
                    System.out.println("UJHNDNJSKNDKJSNKJSD");
                    if ((gameMap[level][j-mode][gameMapj] == 2)) {
                        System.out.println("RECOGNIZES BRICK BRICK"+j+brickj);
                        gameMap[level][j-mode][gameMapj] = 0;
                        bricktype = 2;
                        bricki = i;
                        brickj = j-mode;
                    }
                    if ((gameMap[level][j-mode][gameMapj] == 3)) {
                        bricktype = 3;
                        bricki = i;
                        brickj = j-mode;
                    }
                    jump = 0;
                    count = 0;
                    gravity = 1;
                    jumpeffect = 0;
                }
            }

        }
    }
    public void gravity(int level, int currentColumn) {

       if ((j < 7)&&(gravity == 1)&&(gameMapj-1>0)&&(level < 3) ){
            if ((gameMap[level][j+1][gameMapj-1] == 0)||(gameMap[level][j+1][gameMapj-1] >3)) {  //checks if the spot diagonally downwards is free
                if((gameMap[level][j][gameMapj-1] == 0)||  ((gameMap[level][j][gameMapj-1] >3)&&(gameMap[level][j][(gameMapj - 1)] != 7))  ) {   //checks if the spot one box in front of mario is free
                    j++;
                    gravityeffect = 1;
                } else{
                    if((gameMap[level][j+1][gameMapj] == 0)||   ((gameMap[level][j+1][gameMapj] >3)&&(gameMap[level][j][(gameMapj - 1)] != 7))   ) {  //checks if the spot below mario is free
                        collision = 1;
                        System.out.println("COLLLLISION");
                    }
                        jumping = 0;
                        gravityeffect = 0;
                        jumpeffect = 0;

                }
            } else{
                if( ((gameMap[level][j+1][gameMapj] == 0)||(gameMap[level][j+1][gameMapj] >3))) {  //checks if the spot below mario is free
                    j++;
                    gravityeffect = 1;
                    collision = 2;
                }
                jumping = 0;
                gravityeffect = 0;
                jumpeffect = 0;
            }

        }else{
            jumping = 0;
            gravityeffect = 0;
        }
       // System.out.println("GRAVITY"+gravityeffect+"JUMP"+jumpeffect);




    }
}