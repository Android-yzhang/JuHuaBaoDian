package com.hopen.JewelTD.Beans;

/**
 * Created by yzhang on 2017/3/9.
 */

public class Position {

    int PositionX;
    int PositionY;

    public Position(int positionX, int positionY) {
        PositionX = positionX;
        PositionY = positionY;
    }

    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public void move(int x , int y){
        PositionX = PositionX - x;
        PositionY = PositionY - y;
    }
}
