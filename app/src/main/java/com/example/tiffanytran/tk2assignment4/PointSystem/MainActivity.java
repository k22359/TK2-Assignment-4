package com.example.tiffanytran.tk2assignment4.PointSystem;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tiffanytran.tk2assignment4.PointSystem.ScreenView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ScreenView screenView = new ScreenView(this);
        setContentView(screenView);
    }
}
