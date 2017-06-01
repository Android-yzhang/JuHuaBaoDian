package com.hopen.JewelTD;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hopen.JewelTD.GameController.GameController;
import com.hopen.JewelTD.Views.JewelTDGameView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yzhang on 2017/3/2.
 */

public class JewelTDMainActivity extends AppCompatActivity {

    JewelTDGameView gameView;
    GameController controller;
    public static int BOX_SIZE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*隐藏标题栏*/
        getSupportActionBar().hide();
        /*隐藏状态栏*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameView = new JewelTDGameView(this,null);
        setContentView(gameView);
        controller = new GameController(this , gameView);
        gameView.setGameController(controller);
    }
}
