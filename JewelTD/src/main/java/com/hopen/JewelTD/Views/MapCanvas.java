package com.hopen.JewelTD.Views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hopen.JewelTD.Beans.Enemy;
import com.hopen.JewelTD.Beans.GameSpirit;
import com.hopen.JewelTD.R;

import java.util.List;

/**
 * Created by yzhang on 2017/3/6.
 */

public class MapCanvas extends View {
    public MapCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSpirits(List<Enemy> enemy_list , List<GameSpirit> GameSpirit_list){

    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        super.layout(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        canvas.drawColor(0);
    }
}
