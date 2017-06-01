package com.hopen.JewelTD.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.ThumbnailUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hopen.JewelTD.Beans.Jewel;
import com.hopen.JewelTD.R;

import static com.hopen.JewelTD.JewelTDMainActivity.BOX_SIZE;


/**
 * Created by yzhang on 2017/3/6.
 */

public class MapNode extends View {

    private int PositionX, PositionY;
    private Jewel jewel;

    public MapNode(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPosition(int x, int y) {
        PositionX = x;
        PositionY = y;
    }

    public void setJewel(Jewel jewel) {
        this.jewel = jewel;
    }

    @Override
    public void layout(int left, int top, int right, int bottom) {
        super.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (PositionX == 4 && PositionY > 4 && PositionY < 16) {
            canvas.drawColor(0xffdfdfdf);
        } else if (PositionX == 4 && PositionY == 16) {
            canvas.drawColor(0xffeeeeee);
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE);
            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            int baseline = (BOX_SIZE - fontMetrics.bottom - fontMetrics.top) / 2;
//            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("1", BOX_SIZE / 2, baseline, p);
//            Rect mBound = new Rect();
//            p.getTextBounds("1", 0, 1, mBound);
//            canvas.drawText("1" , BOX_SIZE / 2 - mBound.width() / 2 , BOX_SIZE / 2 + mBound.height() / 2 , p);
//            canvas.drawText("1" , BOX_SIZE - mBound.width(), BOX_SIZE - mBound.height() , p);
        } else if (PositionY == 16 && PositionX > 4 && PositionX < 28) {
            canvas.drawColor(0xffdfdfdf);
        } else if (PositionX == 28 && PositionY == 16) {
            canvas.drawColor(0xffeeeeee);
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE);
            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            int baseline = (BOX_SIZE - fontMetrics.bottom - fontMetrics.top) / 2;
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("2", BOX_SIZE / 2, baseline, p);
        } else if (PositionX == 28 && PositionY > 4 && PositionY < 16) {
            canvas.drawColor(0xffdfdfdf);
        } else if (PositionX == 28 && PositionY == 4) {
            canvas.drawColor(0xffeeeeee);
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE);
            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            int baseline = (BOX_SIZE - fontMetrics.bottom - fontMetrics.top) / 2;
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("3", BOX_SIZE / 2, baseline, p);
        } else if (PositionY == 4 && PositionX > 16 && PositionX < 28) {
            canvas.drawColor(0xffdfdfdf);
        } else if (PositionX == 16 && PositionY == 4) {
            canvas.drawColor(0xffeeeeee);
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE);
            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            int baseline = (BOX_SIZE - fontMetrics.bottom - fontMetrics.top) / 2;
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("4", BOX_SIZE / 2, baseline, p);
        } else if (PositionX == 16 && PositionY > 4 && PositionY < 28) {
            canvas.drawColor(0xffdfdfdf);
        } else if (PositionX == 16 && PositionY == 28) {
            canvas.drawColor(0xffeeeeee);
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE);
            Paint.FontMetricsInt fontMetrics = p.getFontMetricsInt();
            int baseline = (BOX_SIZE - fontMetrics.bottom - fontMetrics.top) / 2;
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("5", BOX_SIZE / 2, baseline, p);
        } else if (PositionY == 28 && PositionX > 16 && PositionX < 28) {
            canvas.drawColor(0xffdfdfdf);
        } else if ((PositionX + PositionY) % 2 == 0) {
            canvas.drawColor(0xff80ff80);
        } else {
            canvas.drawColor(0xffffff80);
        }
        if (jewel != null) {
            //图片
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), jewel.getRes_id());
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, (int) (BOX_SIZE * (jewel.getScale() * 2 - 1)), (int) (BOX_SIZE * (jewel.getScale() * 2 - 1)), ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
            canvas.drawBitmap(bitmap, (1 - jewel.getScale()) * BOX_SIZE, (1 - jewel.getScale()) * BOX_SIZE, p);
            bitmap.recycle();
            //文字
            p.setColor(Color.BLACK);
            p.setTextSize(BOX_SIZE / 3);
            p.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(jewel.getShort_name(), BOX_SIZE / 2, BOX_SIZE, p);
        }
    }
}
