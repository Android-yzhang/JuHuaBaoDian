package com.hopen.JewelTD.Beans;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.hopen.JewelTD.Views.MapNode;

import java.util.List;

/**
 * Created by yzhang on 2017/3/8.
 */

public class Jewel {
    /**
     * 名称
     * 缩略名
     * 攻击
     * 攻速
     * 攻击范围
     * 技能
     * res_id
     * 贴图
     * 合成表
     * 经验
     * 状态
     */
    String name;
    String short_name;
    int attack;
    int attack_speed;
    int attack_scope;
    int[] skills;
    int res_id;
    float scale;
    int compose;
    int Exp;
    int[] State;
    Position position;

    /**
     * 攻击模式
     * 默认：0
     * 血量优先：1
     * buff优先：2
     * 位置最前：3
     * 位置最后：4
     */
    int MODE = 0;

    MapNode view;

    public Jewel(String name, String short_name, int attack, int attack_speed,
                 int attack_scope, int[] skills, int res_id, int compose, int Exp, int[] State) {
        this(name, short_name, attack, attack_speed, attack_scope, skills, res_id, 1, compose, Exp, State);
    }

    public Jewel(String name, String short_name, int attack, int attack_speed,
                 int attack_scope, int[] skills, int res_id, float scale, int compose, int Exp, int[] State) {
        this.name = name;
        this.short_name = short_name;
        this.attack = attack;
        this.attack_speed = attack_speed;
        this.attack_scope = attack_scope;
        this.skills = skills;
        this.res_id = res_id;
        this.scale = scale;
        this.compose = compose;
        this.Exp = Exp;
        this.State = State;
    }

    public void setView(MapNode view){
        this.view = view;
    }

    public MapNode getView(){
        return view;
    }

    public void setPosition(Position p) {
        position = p;
    }

    public String getShort_name() {
        return short_name;
    }

    public int getRes_id() {
        return res_id;
    }

    public float getScale() {
        return scale;
    }

    public GameSpirit attack(List<Enemy> list) {
        if (MODE == 0) {
            for (int i = 0; i < list.size(); i++) {
                Position p = list.get(i).getPosition();
                if (((p.getPositionX() - position.getPositionX()) ^ 2 + (p.getPositionY() - position.getPositionY()) ^ 2) < (attack_scope ^ 2)) {
                    return new GameSpirit(this , list.get(i) , res_id , position , 10);// TODO: 2017/3/9
                }
            }
        }
        return null;
    }

}
