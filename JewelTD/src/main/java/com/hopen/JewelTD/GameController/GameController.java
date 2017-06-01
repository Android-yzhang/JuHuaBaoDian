package com.hopen.JewelTD.GameController;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.hopen.JewelTD.Beans.Enemy;
import com.hopen.JewelTD.Beans.GameSpirit;
import com.hopen.JewelTD.Beans.Jewel;
import com.hopen.JewelTD.Data.JewelMap;
import com.hopen.JewelTD.Views.JewelTDGameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yzhang on 2017/3/8.
 */

public class GameController {
    /**
     * State,当前游戏所在阶段
     * 放置阶段，不允许合成，round_jewel.size() == 5; ，开始保留阶段,State = 0;
     * 保留阶段，可以合成，round_jewel保留一个，开始游戏阶段，保留的加入jewel_list,State = 1;
     * 游戏阶段，可以合成，Enemy.size() == 0; 回合+1，开始放置阶段,State = 2;
     */
    private int State = 0;

    /**
     * 是否可以合成
     */
    public boolean can_Compose = false;
    /**
     * 游戏回合数
     */
    private int round;

    JewelTDGameView jewelTDGameView;
    Context context;

    /**
     * 宝石列表
     */
    List<Jewel> jewel_list = new ArrayList<>();

    /**
     * 怪物列表 Enemy[10]
     */
    List<Enemy> enemy_list;

    /**
     * 绘制物列表
     */
    List<GameSpirit> GameSpirit_list = new ArrayList<>();

    /**
     * 回合内，放置的宝石列表
     */
    List<Jewel> round_jewel_list = new ArrayList<>();

    private Handler mHandler = new Handler();

    public GameController(Context context , JewelTDGameView view){
        this.context = context;
        jewelTDGameView = view;
        startRound();
    }

    /**
     * 放置阶段
    */
    private void startRound(){
        State = 0;
        can_Compose = false;
    }

    public void addJewel(Jewel jewel){
        round_jewel_list.add(jewel);
        if (round_jewel_list.size() == 5){
            StartSaveJewel();
        }
    }

    /**
     * 保留阶段
     */
    private void StartSaveJewel(){
        State++;
        can_Compose = true;
        Toast.makeText(context , "请选择保留一个宝石" , Toast.LENGTH_SHORT).show();
    }

    public void SaveJewel(){

    }

    public void SaveJewel(Jewel jewel){
        for(int i = 0 ; i < round_jewel_list.size() ; i++){
            if (round_jewel_list.get(i) != jewel){
//                round_jewel_list.get(i).getView().setJewel();
            }
        }
        round_jewel_list.clear();
        jewel_list.add(jewel);
        startGame();
    }

    /**
     * 游戏阶段
     */
    private void startGame(){
        State++;
        startGameProcess();
    }

    private void startGameProcess(){
        enemy_list.add(new Enemy(500 , 0 , 300 , 0 , null , null));
        mHandler.postDelayed(GameFrame , 20);
    }

    private Runnable GameFrame = new Runnable() {
        @Override
        public void run() {
            int enemy_list_size = enemy_list.size();
            int jewel_list_size = jewel_list.size();
            if(enemy_list_size != 0){
                for(int i = 0 ; i < enemy_list_size ; i++){
                    enemy_list.get(i).move(1,1);
                }
                for(int i = 0 ; i < jewel_list_size ; i++){
                    GameSpirit gameSpirit = jewel_list.get(i).attack(enemy_list);
                    if(gameSpirit != null){
                        GameSpirit_list.add(gameSpirit);
                    }
                }
                jewelTDGameView.DrawGameCanvas(enemy_list , GameSpirit_list);
                mHandler.postDelayed(GameFrame , 20);
            }else{
                startNextRound();
            }
        }
    };

    public void startNextRound(){
        round++;
        startRound();
    }

    /**
     * 功能函数
     */
    
    public Jewel RandomJewel(){
        // TODO: 2017/3/9 其他规则
        Random random = new Random();
        int i = random.nextInt(100);
        return JewelMap.Jewels.get(i);
    }
}
