package com.example.tiffanytran.tk2assignment4.Control;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

/**
 * Created by tiffanytran on 5/29/17.
 */

public class GameThread extends Thread{
    private ScreenView screenView;
    private Controller controller;

    public GameThread(Controller controller, ScreenView screenView){
        this.screenView = screenView;
        this.controller = controller;
    }

    public void run(){
        SurfaceHolder sh = screenView.getHolder();
        while(true){
            Canvas canvas = sh.lockCanvas();
            if(canvas != null){
                screenView.draw(canvas); //calls the draw function inside our GameView
                controller.update();
                sh.unlockCanvasAndPost(canvas);
            }
            try{
                sleep(50);
            }
            catch(InterruptedException e) {
                System.out.println("Exception occurred");
            }
        }
    }
}
