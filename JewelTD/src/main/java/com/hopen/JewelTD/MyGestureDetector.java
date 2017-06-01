package com.hopen.JewelTD;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/**
 * 手势识别类
 * Created by yzhang on 2017/3/6.
 */

public class MyGestureDetector {

    public interface MyGestureDetectorListener{
        void onClick(MotionEvent e);
        void onLongClickBegin(MotionEvent e);
        void onLongClick(MotionEvent e);
        void onMove(int x , int y);
        void onScale(float scale);
    }

    private MyGestureDetectorListener listener;

    private Handler handler = new Handler();

    public MyGestureDetector(MyGestureDetectorListener listener){
        this.listener = listener;
    }

    // 触摸位置
    private int start_x, start_y, end_x, end_y;
    // 两触点距离
    private double beforeLenght, afterLenght;
    // 缩放比例
    private float scale_temp;

    /**
     * 模式 NONE：无 DRAG：拖拽. ZOOM:缩放
     */
    private final int NONE = 0;
    private final int DRAG = 1;
    private final int ZOOM = 2;
    private int TYPE = NONE;// 默认模式

    private final int ONCLICK_TIMEOUT = 300;
    private final int LONG_CLICK_TIME = 2000;

    private boolean isOnClick = false;
    private boolean isOnLongClick = false;
    MotionEvent clickEvent;

    public boolean onTouchEvent(MotionEvent event) {
        /** 处理单点、多点触摸 **/
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                onTouchDown(event);
                break;
            // 多点触摸
            case MotionEvent.ACTION_POINTER_DOWN:
                onPointerDown(event);
                break;

            case MotionEvent.ACTION_MOVE:
                onTouchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                if(isOnClick){
                    listener.onClick(event);
                    isOnClick = false;
                }
                TYPE = NONE;
                break;

            // 多点松开
            case MotionEvent.ACTION_POINTER_UP:
                TYPE = NONE;
                break;
        }
        return true;
    }

    /** 按下 **/
    void onTouchDown(MotionEvent event) {
        TYPE = DRAG;
        start_x = (int) event.getX();
        start_y = (int) event.getY();
        isOnClick = true;
        clickEvent = event;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isOnClick){
                    isOnClick = false;
                    isOnLongClick = true;
                    listener.onLongClickBegin(clickEvent);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isOnLongClick){
                                listener.onLongClick(clickEvent);
                            }
                        }
                    },LONG_CLICK_TIME-ONCLICK_TIMEOUT);
                }
            }
        },ONCLICK_TIMEOUT);
    }

    /** 两个手指 只能放大缩小 **/
    void onPointerDown(MotionEvent event) {
        isOnClick = false;
        if (event.getPointerCount() == 2) {
            TYPE = ZOOM;
            beforeLenght = getDistance(event);// 获取两点的距离
        }
    }

    /** 移动的处理 **/
    void onTouchMove(MotionEvent event) {
        if(Math.abs(event.getX() + event.getY() - start_x - start_y) < 5){
            return;
        }
        isOnClick = false;
        isOnLongClick = false;
        /** 处理拖动 **/
        if (TYPE == DRAG) {
            end_x = (int) event.getX();
            end_y = (int) event.getY();
            listener.onMove(end_x - start_x , end_y - start_y);
            start_x = end_x;
            start_y = end_y;
        }
        /** 处理缩放 **/
        else if (TYPE == ZOOM) {
            afterLenght = getDistance(event);// 获取两点的距离
            float gapLength = (float) (afterLenght - beforeLenght);// 变化的长度
            if (Math.abs(gapLength) > 5f) {
                scale_temp = (float) (afterLenght / beforeLenght);// 求的缩放的比例
                listener.onScale(scale_temp);
                beforeLenght = afterLenght;
            }
        }

    }

    /** 获取两点的距离 **/
    double getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return Math.sqrt(x * x + y * y);
    }
}
