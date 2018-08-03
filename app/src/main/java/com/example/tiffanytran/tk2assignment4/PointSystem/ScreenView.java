package com.example.tiffanytran.tk2assignment4.PointSystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.tiffanytran.tk2assignment4.BackgroundObjects.Brick;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.ImmobileObjects;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.NoHitBrick;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.Numbers;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.greenPipe;
import com.example.tiffanytran.tk2assignment4.BackgroundObjects.itemBrick;
import com.example.tiffanytran.tk2assignment4.Control.Controller;
import com.example.tiffanytran.tk2assignment4.Enemies.Beetle;
import com.example.tiffanytran.tk2assignment4.Enemies.Koopa;
import com.example.tiffanytran.tk2assignment4.Items.Coin;
import com.example.tiffanytran.tk2assignment4.Items.Flower;
import com.example.tiffanytran.tk2assignment4.Items.Mushroom;
import com.example.tiffanytran.tk2assignment4.Mario.FireMario;
import com.example.tiffanytran.tk2assignment4.Mario.SuperMario;
import com.example.tiffanytran.tk2assignment4.Mario.regMario;
import com.example.tiffanytran.tk2assignment4.R;

/**
 * Created by tiffanytran on 5/24/17.
 */

public class ScreenView extends SurfaceView implements SurfaceHolder.Callback{
    public ScreenView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        System.out.println("Constructor");

    }
    int rowLength = 17;
    int columnLength = 9;
    public int gameMap[][][] = new int [4][columnLength][];
    public int gameMap2[][] = new int [columnLength][];
    int rowSize;
    public int columnSize;
    int width;
    int height;
    public Numbers num = new Numbers();

    public PointsCalculator poi = new PointsCalculator();
    public NoHitBrick noh[] = new NoHitBrick[200];
    public Brick bri[] = new Brick[200];
    public greenPipe gre[] = new greenPipe[200];
    public itemBrick ite[] = new itemBrick[200];
    public Coin coi[] = new Coin[200];
    public Flower flo[] = new Flower[200];
    public Mushroom mus[] = new Mushroom[200];
    public Koopa koopa[] = new Koopa[200];
    public Beetle beetle[] = new Beetle[200];
    public Rect rect = new Rect();
    public Bitmap icons [] = new Bitmap[20];
    public Bitmap marioIcons [] = new Bitmap[20];
    public Bitmap superIcons [] = new Bitmap[20];
    public Bitmap fireIcons [] = new Bitmap[20];
    public regMario littleMario = new regMario(this);
    public SuperMario superMario = new SuperMario(this);
    public FireMario fireMario = new FireMario(this);


    Controller controller = new Controller(this);

    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE); //draws the background as white
        rowSize = width / rowLength;
        columnSize = height / columnLength;

        rect.set(0, 0, width, height);
        canvas.drawBitmap(icons[11], null, rect, null); // draws background on screen

        for(int i=0; i < rowLength; i++){
            rect.set(i*rowSize, columnSize*8, (i*rowSize)+rowSize+10, columnSize*9); //draws the background bricks in bottom row
            canvas.drawBitmap(icons[7], null, rect, null);
        }

        rect.set(width-300, 750, width-150, 900);
        canvas.drawBitmap(icons[8], null, rect, null); // draws fireball button on screen

        if(num.marioNum == 0){
            rect.set(littleMario.i*rowSize, columnSize*littleMario.j, (littleMario.i*rowSize)+rowSize, (columnSize*littleMario.j)+columnSize);
            canvas.drawBitmap(marioIcons[littleMario.iconNum], null, rect, null); //this draws little Mario

        } else if(num.marioNum == 1){
            rect.set(littleMario.i*rowSize, columnSize*littleMario.j-columnSize, (littleMario.i*rowSize)+rowSize, (columnSize*littleMario.j)+(columnSize));
            canvas.drawBitmap(superIcons[superMario.iconNum], null, rect, null); //this draws super Mario

        } else if(num.marioNum == 2){
            rect.set(littleMario.i*rowSize, columnSize*littleMario.j-columnSize, (littleMario.i*rowSize)+rowSize, (columnSize*littleMario.j)+(columnSize));
            canvas.drawBitmap(fireIcons[fireMario.iconNum], null, rect, null); //this draws fire Mario
            int counter = 0;

            //this part draws the fireball, shooting from fire Mario's position
            int fireballX = fireMario.i - num.fireCounter;
            int fireballY = fireMario.j;
            if (fireMario.i - num.fireCounter >= 0) {
                if (counter == 0) {
                    if (num.fireCounter == 1 || num.fireCounter == 3 || num.fireCounter == 5 || num.fireCounter == 7) {
                        rect.set(fireballX * rowSize, columnSize * fireballY, (fireballX * rowSize) + rowSize / 2, (columnSize * fireballY) + columnSize / 2);
                        canvas.drawBitmap(icons[fireMario.attackiconNum], null, rect, null);

                        for (int j = 0; j < num.koopaNum; j++) {
                            if (koopa[j].exists == 1) {
                                if((fireballX == koopa[j].i)  && (fireballY == koopa[j].j)) {
                                    koopa[j].exists = 0; //if Koopa is hit with a fireball, he dies
                                }

                            }
                        }

                    }
                    counter++;
                }

                if (counter == 1) { //the counter is used to show the fireball "bouncing", since the rectangle is changed so it looks like it bounces
                    if (num.fireCounter == 2 || num.fireCounter == 4 || num.fireCounter == 6 || num.fireCounter == 8) {
                        rect.set((fireballX * rowSize), (height / 11) * (fireballY + 2), ((fireballX * rowSize)) + rowSize / 2, (((height / 11) * (fireballY + 2)) + columnSize / 2));
                        canvas.drawBitmap(icons[fireMario.attackiconNum], null, rect, null);



                        for (int j = 0; j < num.koopaNum; j++) {
                            if (koopa[j].exists == 1) {
                                if((fireballX == koopa[j].i)  && (fireballY == koopa[j].j)) {
                                    koopa[j].exists = 0;  //if Koopa is hit with a fireball, he dies
                                }

                            }
                        }

                    }
                    counter--;
                }

            }
        }

        for(int i= 0; i < num.nohNum; i++) {
            if (noh[i].exists == 1) {
                rect.set(noh[i].i*rowSize, noh[i].j*columnSize, (noh[i].i+1)*rowSize, (noh[i].j+1)*columnSize);
                canvas.drawBitmap(icons[noh[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.briNum; i++) {
            if (bri[i].exists == 1) {
                rect.set(bri[i].i*rowSize, bri[i].j*columnSize, (bri[i].i+1)*rowSize, (bri[i].j+1)*columnSize);
                canvas.drawBitmap(icons[bri[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.iteNum; i++) {
            if (ite[i].exists == 1) {
                rect.set(ite[i].i*rowSize, ite[i].j*columnSize, (ite[i].i+1)*rowSize, (ite[i].j+1)*columnSize);
                canvas.drawBitmap(icons[ite[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.coiNum; i++) {
            if(coi[i].exists == 1){
                rect.set(coi[i].i*rowSize, coi[i].j*columnSize, (coi[i].i+1)*rowSize, (coi[i].j+1)*columnSize);
                canvas.drawBitmap(icons[coi[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.floNum; i++) {
            if (flo[i].exists == 1) {
                rect.set(flo[i].i*rowSize, flo[i].j*columnSize, (flo[i].i+1)*rowSize, (flo[i].j+1)*columnSize);
                canvas.drawBitmap(icons[flo[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.musNum; i++) {
            if (mus[i].exists == 1) {
                rect.set(mus[i].i*rowSize, mus[i].j*columnSize, (mus[i].i+1)*rowSize, (mus[i].j+1)*columnSize);
                canvas.drawBitmap(icons[mus[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.greNum; i++) {
            if (gre[i].exists == 1) {
                rect.set(gre[i].i*rowSize, gre[i].j*columnSize, (gre[i].i+1)*rowSize, (gre[i].j+2)*columnSize);
                canvas.drawBitmap(icons[gre[i].iconNum], null, rect, null);

                if(gre[i].monsterExists == 1) {
                    rect.set(gre[i].monsteri * rowSize, gre[i].monsterj * columnSize, (gre[i].monsteri + 1) * rowSize, (gre[i].monsterj + 1) * columnSize);
                    canvas.drawBitmap(icons[gre[i].monIconNum], null, rect, null);
                }
            }
        }
        for(int i= 0; i < num.koopaNum; i++) {
            if (koopa[i].exists == 1) {
                rect.set(koopa[i].i*rowSize, koopa[i].j*columnSize, (koopa[i].i+1)*rowSize, (koopa[i].j+1)*columnSize);
                canvas.drawBitmap(icons[koopa[i].iconNum], null, rect, null);
            }
        }
        for(int i= 0; i < num.beetleNum; i++) {
            if (beetle[i].exists == 1) {
                rect.set(beetle[i].i*rowSize, beetle[i].j*columnSize, (beetle[i].i+1)*rowSize, (beetle[i].j+1)*columnSize);
                canvas.drawBitmap(icons[beetle[i].iconNum], null, rect, null);
            }
        }

        //prints out lives
        for (int i = 0; i < littleMario.lives; i++){
            rect.set(1500+((width/20)*i), 0, 1500+((width/20)*(i+1)), 125);
            canvas.drawBitmap(marioIcons[0], null, rect, null);
        }

        //prints out level
        Paint level = new Paint(Color.GREEN);
        level.setTextSize(40);
        String levelString;
        levelString = Integer.toString(num.level+1);
        level.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Level:"+levelString, 700, 20, level);

        //prints You Win when the player finishes all three levels
        if((num.level+1) > 3){
            Paint scoreWin = new Paint(Color.GREEN);
            canvas.drawColor(Color.WHITE); //this makes the background white
            scoreWin.setTextSize(80); //this sets the text size
            String Score = "You win!";
            canvas.drawText(Score, 425, getHeight()/2-100, scoreWin);
        }

        //prints out score
        Paint score = new Paint(Color.GREEN);
        score.setTextSize(60);
        String stringOfNumber;
        stringOfNumber = Integer.toString(poi.points);
        score.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(stringOfNumber, 30, 20, score);

        if(littleMario.lives < 0) { //ends game when Mario dies by drawing a new ending screen
            Paint scoreColor = new Paint(Color.GREEN);
            canvas.drawColor(Color.WHITE); //this makes the background white
            scoreColor.setTextSize(80); //this sets the text size
            String Score = "Game Over!";
            canvas.drawText(Score, 425, getHeight()/2-100, scoreColor);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) { //29

        width = getWidth();
        System.out.println("width is"+width);
        height = getHeight();
        System.out.println("height is" + height);
        rowSize = width / rowLength;
        columnSize = height / columnLength;
        num.rowLength = rowLength;
        num.columnLength = columnLength;
        num.width = width;
        num.height = height;

        //for  level 1
        gameMap[0][0] = new int []{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //LEVEL 2: {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //LEVEL 1: {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //LEVEL 3: {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,4,4,0,0,4,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][1] = new int []{0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,4,4,4,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,  4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,4,4,4,0,0,0,0,0,0,0,    0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,    0,0,4,4,0,0,0,4,4,4,0,0,0,0,0,0,0,   0,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,0,   0,0,4,4,4,4,0,0,0,1,1,0,0,0,0,0,0,   0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,4,0,  4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][2] = new int []{0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,  2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{1,1,1,0,0,2,2,1,2,2,0,0,0,0,0,0,0,    0,0,0,0,0,0,4,3,4,0,0,0,0,0,0,0,0,     0,1,1,1,0,0,0,0,0,2,2,2,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,1,2,2,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,4,4,4,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{2,2,2,0,0,0,0,2,1,1,1,0,0,0,0,0,0,   0,0,2,2,2,2,1,0,0,4,4,0,0,4,4,4,4,   0,0,0,0,0,2,2,2,1,0,4,4,4,4,0,1,1,  1,0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][3] = new int []{0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,2,1,2,1,0,0,0,0,0,0,0,  0,0,0,0,0,0,4,4,0,0,2,2,0,0,0,0,0,  0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,    0,2,1,2,2,0,0,0,0,0,0,0,4,4,4,0,0,     0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0};  //{0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,    0,0,0,0,0,0,2,1,2,1,2,2,0,0,0,0,0,   0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,   0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,   0,0,0,0,0,0,4,4,4,0,0,0,0,1,3,1,1,   1,2,2,0,0,0,0,0,0,0,2,2,3,2,0,0,0,  0,0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][4] = new int []{0,0,4,4,0,0,4,4,4,0,0,0,4,4,3,4,0,  0,0,0,0,0,0,4,4,0,0,0,1,1,1,0,0,0,  0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,  2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,2,2,2,0,0,0,0,0,1,1,0,0,0,0,0,    0,0,0,0,0,0,1,2,1,0,0,0,2,2,2,0,0,     0,0,0,2,1,2,0,0,0,0,4,4,0,0,0,0,0,    0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,4,4,0,0,4,4,4,0,0,0,4,4,4,0,0,    0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,3,0,0,0,0,0,2,1,2,0,   0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,4,1,1,0,0,0,0,0,0,0,0,0,0,   0,4,4,0,0,0,2,3,2,0,0,4,0,0,0,0,0,   0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,  0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][5] = new int []{0,0,0,0,0,0,0,1,2,2,0,0,0,0,0,0,0,  0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,4,4,4,4,0,0,0,0,0,0,0,1,0,4,4,  4,3,2,2,0,6,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,4,1,1,1,1,4,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,     0,0,0,0,0,4,4,4,0,0,0,0,2,3,2,0,0,    2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,1,1,1,0,0,0,0,0,0,0,2,1,2,2,0,    0,0,0,0,4,0,2,2,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,2,2,   2,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,   0,2,0,0,4,4,0,0,0,0,0,0,2,2,2,0,0,  0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][6] = new int []{0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,  0,0,0,0,4,0,2,2,0,0,0,0,0,2,2,0,0,  0,0,3,2,0,0,0,1,0,0,0,0,7,0,2,0,0,  0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,3,0,0,0,0,1,1,1,1,1,1,0,0,0,    0,0,0,7,0,7,0,0,0,7,7,0,0,0,0,1,0,     0,1,0,0,0,2,3,2,0,0,1,1,0,0,0,0,0,    0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0};  //{0,2,2,0,0,0,2,1,2,3,2,0,0,0,0,0,0,    0,0,0,4,7,4,4,0,0,1,1,1,0,0,0,0,0,   0,2,2,0,0,0,0,1,0,0,0,0,7,0,0,0,0,   0,0,3,0,0,1,1,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,4,2,2,1,2,0,0,0,4,0,0,1,0,0,   0,0,0,1,0,0,0,0,0,0,7,2,0,1,2,2,0,   0,0,0,0,1,1,2,1,0,3,0,7,0,0,4,4,4,  0,1,1,7,0,2,2,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][7] = new int []{0,1,1,1,1,1,9,0,4,4,9,4,7,1,1,1,0,  9,1,1,0,0,0,2,2,1,1,1,1,1,9,4,4,4,  0,0,0,0,0,1,1,1,1,1,0,0,1,0,0,1,1,  1,0,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0};   //{4,4,4,0,0,0,4,1,1,1,1,1,1,1,1,4,0,    0,0,1,0,0,0,0,0,0,0,0,0,4,4,4,1,0,     1,1,1,0,0,0,0,0,0,1,1,1,0,4,4,4,0,    0,6,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,0,0,0,0,0,0,4,4,4,4,7,0,0,0,0,    0,1,1,0,0,0,0,0,1,1,1,1,1,4,4,0,0,   0,4,4,4,0,1,1,1,1,1,0,0,0,0,0,0,0,   0,0,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0};   //{1,1,0,0,0,0,0,0,4,7,4,4,1,1,1,1,0,   4,0,1,1,0,4,4,4,4,1,0,0,0,0,0,0,0,   4,4,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  4,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0};
        gameMap[0][8] = new int []{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};  //{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};   //{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        //for level 2
        gameMap[1][0] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][1] = new int[]{0,0,0,0,0,0,0,4,4,4,0,0,0,0,0,0,0,    0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][2] = new int[]{1,1,1,0,0,2,2,1,2,2,0,0,0,0,0,0,0,    0,0,8,0,0,0,4,3,4,0,0,0,0,0,0,0,0,     0,1,1,1,0,0,0,0,0,2,2,2,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][3] = new int[]{0,0,0,8,0,0,0,0,0,0,4,4,0,0,0,0,0,    0,2,1,2,2,0,0,0,0,0,0,0,4,4,4,0,0,     0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][4] = new int[]{0,0,2,2,2,0,0,0,0,0,1,1,0,0,0,0,0,    0,0,0,0,0,0,1,2,1,0,0,0,2,2,2,0,0,     0,0,0,2,1,0,0,0,0,0,4,4,0,0,0,0,0,    0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][5] = new int[]{0,0,0,0,0,0,0,0,4,1,1,1,1,4,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0,     0,0,0,0,0,4,4,4,0,0,0,0,0,3,2,0,0,    2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][6] = new int[]{0,0,0,3,0,0,0,0,1,1,1,1,1,1,0,0,0,    0,0,0,7,0,7,0,0,0,7,7,0,0,0,0,1,0,     0,1,0,0,0,2,3,2,0,0,1,1,0,0,0,0,0,    0,0,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][7] = new int[]{4,4,4,9,0,0,4,1,1,1,1,1,1,1,1,4,0,    0,0,1,1,9,1,0,0,9,1,1,0,4,4,4,1,0,     1,1,1,0,0,0,9,0,0,1,1,1,0,4,4,4,0,    0,0,0,7,0,0,1,0,0,0,0,0,0,0,0,0,0};
        gameMap[1][8] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,     0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,    0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        //for level 3
        gameMap[2][0] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,4,4,0,0,4,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][1] = new int[]{0,0,0,0,0,0,0,0,0,8,0,0,0,4,4,4,0,   0,0,4,4,4,4,0,0,0,1,1,0,0,0,0,0,0,   0,0,1,1,0,0,8,0,0,0,0,0,0,0,0,4,0,  4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][2] = new int[]{2,2,2,0,0,0,0,2,1,1,1,0,0,0,0,0,0,   0,0,2,2,2,2,1,0,0,4,4,0,0,4,4,4,4,   0,0,0,0,0,2,2,2,1,0,4,4,4,4,0,1,1,  1,0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][3] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,   0,0,0,0,0,0,4,4,4,0,0,0,0,1,3,1,1,   1,2,2,0,0,0,0,0,0,0,2,2,3,2,0,0,0,  0,0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][4] = new int[]{0,0,0,0,4,1,1,0,0,0,0,0,0,0,0,0,0,   0,4,4,0,0,0,2,3,2,0,0,4,0,0,0,0,0,   0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,  0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][5] = new int[]{0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,2,2,   2,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,   0,2,0,0,4,4,0,0,0,0,0,0,2,2,2,0,0,  0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][6] = new int[]{0,0,0,4,2,2,1,2,0,0,0,4,0,0,1,0,0,   0,0,0,1,0,0,0,0,0,0,7,2,0,1,2,2,0,   0,0,0,0,1,1,2,1,0,3,0,7,0,0,4,4,4,  0,1,1,7,0,2,2,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][7] = new int[]{1,1,0,9,0,0,9,0,4,7,4,4,1,1,1,1,0,   4,0,1,1,0,4,4,4,4,1,1,0,0,0,9,0,0,   4,4,7,0,0,0,9,0,0,0,0,1,0,0,0,0,0,  4,0,0,1,0,4,4,0,0,0,0,0,0,0,0,0,0};
        gameMap[2][8] = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,  0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


        marioIcons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.littlemario);
        marioIcons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.littlemariojump);
        marioIcons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.littlemariomove);
        marioIcons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.littlemario);

        superIcons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.supermario);
        superIcons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.supermariojump);
        superIcons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.supermariomove);
        superIcons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.supermariomove2);

        fireIcons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.firemario);
        fireIcons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.firemariojump);
        fireIcons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.firemariomove);
        fireIcons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.firemariomove2);

        icons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.no_hit_brick);
        icons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bricks);
        icons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.item);
        icons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        icons[4] = BitmapFactory.decodeResource(getResources(), R.drawable.flower);
        icons[5] = BitmapFactory.decodeResource(getResources(), R.drawable.mushroom);
        icons[6] = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        icons[7] = BitmapFactory.decodeResource(getResources(), R.drawable.background_brick);
        icons[8] = BitmapFactory.decodeResource(getResources(), R.drawable.fireball);
        icons[11] = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        icons[10] = BitmapFactory.decodeResource(getResources(), R.drawable.plant);
        icons[12] = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_right);
        icons[13] = BitmapFactory.decodeResource(getResources(), R.drawable.beetle);
        icons[14] = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_left);

        for(int i = 0; i<columnLength; i++) {
            for (int j = (rowLength*4)-1; j >= (rowLength*3); j--) {
                createObject(gameMap[num.level][i][j],i,j%17); //this creates the coordinates of each item in terms of i and j

            }
        }

        controller.startGameThread(); //this starts the thread

    }
    public void createObject(int input, int colNum, int rowNum) { //this sets j as colNum and i as rowNum
        if (input == 0) {
        }
        else {

            if (input == 1) {
                noh[num.nohNum] = new NoHitBrick();
                noh[num.nohNum].createObject(columnSize,rowSize,colNum,rowNum);
                num.nohNum++;
            } else if (input == 2) {
                bri[num.briNum] = new Brick();
                bri[num.briNum].createObject(columnSize,rowSize,colNum,rowNum);
                num.briNum++;
            } else if (input == 3) {
                ite[num.iteNum] = new itemBrick();
                ite[num.iteNum].createObject(columnSize,rowSize,colNum,rowNum);
                ite[num.iteNum].gameMapj = 67 - num.currentColumn; //saves the gameMaps' j value to be used for later
                num.iteNum++;
            } else if (input == 4) {
                coi[num.coiNum] = new Coin();
                coi[num.coiNum].createObject(columnSize,rowSize,colNum,rowNum);
                num.coiNum++;
            } else if (input == 5) {
                flo[num.floNum] = new Flower();
                flo[num.floNum].createObject(columnSize, rowSize, colNum, rowNum);
                num.floNum++;
            } else if (input == 6) {
                mus[num.musNum] = new Mushroom(this);
                mus[num.musNum].createObject(columnSize,rowSize,colNum,rowNum);
                mus[num.musNum].gameMapj = 67 - num.currentColumn;
                num.musNum++;
            } else if (input == 7) {
                gre[num.greNum] = new greenPipe();
                gre[num.greNum].createObject(columnSize, rowSize, colNum, rowNum);
                gre[num.greNum].Monster();
                num.greNum++;
            }
            else if (input == 8) {
                koopa[num.koopaNum] = new Koopa(this);
                koopa[num.koopaNum].createObject(columnSize, rowSize, colNum, rowNum);
                koopa[num.koopaNum].gameMapj = 67 - num.currentColumn; //saves the gameMaps' j value to be used for later
                num.koopaNum++;
            }
            else if (input == 9) {
                beetle[num.beetleNum] = new Beetle(this);
                beetle[num.beetleNum].createObject(columnSize, rowSize, colNum, rowNum);
                beetle[num.beetleNum].gameMapj = 67 - num.currentColumn;
                num.beetleNum++;

            }
        }

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int width = getWidth();
        int height = getHeight();
        if (event.getAction() == MotionEvent.ACTION_DOWN) { //will only get a X and Y coordinate once when user presses down
            int xCoordinate = (int) event.getX();
            int yCoordinate = (int) event.getY();
            if((xCoordinate <= width) && (yCoordinate <= height) && !((xCoordinate >= (width-300)) && (xCoordinate <= (width-150)) && (yCoordinate >= 750) && (yCoordinate <= 900))){
                littleMario.jump = 1;
                superMario.jump = 1;
                fireMario.jump =1;
                return true;
            }

            if((xCoordinate >= (width-300)) && (xCoordinate <= (width-150)) && (yCoordinate >= 750) && (yCoordinate <= 900)){
                fireMario.shoot = 1; //this is when the user clicks on the fireball image
                return true;
            }
        }

        return false;
    }
}
