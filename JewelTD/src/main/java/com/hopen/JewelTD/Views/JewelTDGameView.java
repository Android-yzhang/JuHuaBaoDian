package com.hopen.JewelTD.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hopen.JewelTD.Beans.Enemy;
import com.hopen.JewelTD.Beans.GameSpirit;
import com.hopen.JewelTD.Beans.Jewel;
import com.hopen.JewelTD.Beans.Position;
import com.hopen.JewelTD.Data.JewelMap;
import com.hopen.JewelTD.GameController.GameController;
import com.hopen.JewelTD.MyGestureDetector;

import java.util.List;

import static com.hopen.JewelTD.JewelTDMainActivity.BOX_SIZE;

/**
 * Created by yzhang on 2017/3/2.
 */

public class JewelTDGameView extends ViewGroup implements MyGestureDetector.MyGestureDetectorListener {

    GameController gameController;

    private int[][] map = new int[33][33];
    private MapNode[][] mapNodes = new MapNode[33][33];
    private MapCanvas mapCanvas;

//    private int BOX_SIZE;

    /**
     * 移动相关参数
     */
    private int move_x = 0;
    private int move_y = 0;
    private int window_width = 0;
    private int window_height = 0;
    private int MAX_MOVE_X = 0;
    private int MAX_MOVE_Y = 0;

    private MyGestureDetector myGestureDetector;// 手势识别类

    public JewelTDGameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        myGestureDetector = new MyGestureDetector(this);

        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        window_width = manager.getDefaultDisplay().getWidth();
        window_height = manager.getDefaultDisplay().getHeight();

        BOX_SIZE = window_width / 24;

        move_x = -(16 * BOX_SIZE - window_width / 2);
        move_y = -(16 * BOX_SIZE - window_height / 2);

        MAX_MOVE_X = 33 * BOX_SIZE - window_width;
        MAX_MOVE_Y = 33 * BOX_SIZE - window_height;

        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 33; j++) {
                mapNodes[i][j] = new MapNode(context , null);
                mapNodes[i][j].setPosition(i,j);
                this.addView(mapNodes[i][j]);
            }
        }
        mapCanvas = new MapCanvas(context , null);
        this.addView(mapCanvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 33; j++) {
                mapNodes[i][j].layout(BOX_SIZE * i + move_x , BOX_SIZE * j + move_y ,
                        BOX_SIZE * (i + 1) + move_x, BOX_SIZE * (j + 1) + move_y);
            }
        }
        mapCanvas.layout(0 + move_x , 0 + move_y , BOX_SIZE * 33 + move_x , BOX_SIZE * 33 + move_y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return myGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(MotionEvent e) {
        Log.e("GameView onClick","X:" + ((int)(e.getX() - move_x ) / BOX_SIZE) + " , Y :" + ((int)(e.getY() - move_y) / BOX_SIZE));
        mapCanvas.invalidate();
    }

    @Override
    public void onLongClickBegin(MotionEvent e) {
        Log.e("onLongClickBegin","X:" + e.getX() + " , Y :" + e.getY());
    }

    @Override
    public void onLongClick(MotionEvent e) {
        Log.e("GameView onLongClick","X:" + e.getX() + " , Y :" + e.getY());
        int x = (int)(e.getX() - move_x ) / BOX_SIZE;
        int y = (int)(e.getY() - move_y ) / BOX_SIZE;
        Position position = new Position( x + (BOX_SIZE << 1) , y + (BOX_SIZE << 1));
        // TODO: 2017/3/9
        Jewel jewel = JewelMap.Jewels.get(0);
        jewel.setPosition(position);
        mapNodes[x][y].setJewel(jewel);
        jewel.setView(mapNodes[x][y]);
        mapNodes[x][y].invalidate();
        gameController.addJewel(jewel);
    }

    @Override
    public void onMove(int x, int y) {
        Log.e("GameView onMove","X:" + x + " , Y :" + y);
        boolean isRequest = false;
        if(0 < move_x + x){
            if(move_x != 0){
                isRequest = true;
            }
            move_x = 0;
        }else if(move_x + x < -MAX_MOVE_X){
            if(move_x != -MAX_MOVE_X){
                isRequest = true;
            }
            move_x = -MAX_MOVE_X;
        }else {
            isRequest = true;
            move_x = move_x + x;
        }
        if(0 < move_y + y){
            if(move_y != 0){
                isRequest = true;
            }
            move_y = 0;
        }else if(move_y + y < -MAX_MOVE_Y){
            if(move_y != -MAX_MOVE_Y){
                isRequest = true;
            }
            move_y = -MAX_MOVE_Y;
        }else {
            isRequest = true;
            move_y = move_y + y;
        }
        if(isRequest) {
            requestLayout();
        }
    }

    @Override
    public void onScale(float scale) {
//        Log.e("GameView onScale","scale:" + scale);
        if(BOX_SIZE * scale > window_width / 32 && BOX_SIZE * scale < window_width / 12) {
            BOX_SIZE = (int) (BOX_SIZE * scale);
            MAX_MOVE_X = 33 * BOX_SIZE - window_width;
            MAX_MOVE_Y = 33 * BOX_SIZE - window_height;
            if(move_x * scale > 0){
                move_x = 0;
            }else if(move_x * scale < -MAX_MOVE_X){
                move_x = -MAX_MOVE_X;
            }else{
                move_x = (int) (move_x * scale);
            }
            if(move_y * scale > 0){
                move_y = 0;
            }else if(move_y * scale < -MAX_MOVE_Y){
                move_y = -MAX_MOVE_Y;
            }else{
                move_y = (int) (move_y * scale);
            }
            requestLayout();
        }
    }

    public void setGameController(GameController gameController){
        this.gameController = gameController;
    }

    public void DrawGameCanvas(List<Enemy> enemy_list , List<GameSpirit> GameSpirit_list){
        mapCanvas.setSpirits(enemy_list , GameSpirit_list);
        mapCanvas.invalidate();
    }
}
