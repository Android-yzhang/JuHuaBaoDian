package com.hopen.JewelTD.Beans;

import android.graphics.Bitmap;

/**
 * Created by yzhang on 2017/3/8.
 */

public class Enemy {
    /**
     * 血量
     * 当前血量
     * 回血量
     * 移动速度
     * res_id;
     * 贴图
     * 技能
     * 状态
     */

    double HP;
    double HP_now;
    int HP_plus;
    int speed;
    int res_id;
    Bitmap bitmap;
    int[] skills;
    int[] states;
    Position position;

    public Enemy(double HP , int HP_plus , int speed , int res_id , int[] skills , int[] states){
        this.HP = HP;
        this.HP_now = HP;
        this.HP_plus = HP_plus;
        this.speed  = speed;
        this.res_id = res_id;
        this.skills = skills;
        this.states = states;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return position;
    }

    public void move(int x , int y){
        position.move(x , y);
    }
}
