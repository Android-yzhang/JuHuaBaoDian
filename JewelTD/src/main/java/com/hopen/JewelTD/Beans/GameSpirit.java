package com.hopen.JewelTD.Beans;

import android.graphics.Bitmap;

/**
 * Created by yzhang on 2017/3/9.
 */

public class GameSpirit {
    Jewel from;
    Enemy to;
    int res_id;
    Position position;
    int Speed;

    public GameSpirit(Jewel from, Enemy to, int res_id, Position position, int speed) {
        this.from = from;
        this.to = to;
        this.res_id = res_id;
        this.position = position;
        Speed = speed;
    }

    public void move(int x , int y){
        position.move(x, y);
    }

    public Position getPosition(){
        return position;
    }

    public int getLength(){
        return (int) Math.sqrt((position.getPositionX() - to.getPosition().getPositionX())^2 + (position.getPositionY() - to.getPosition().getPositionY())^2);
    }


}
