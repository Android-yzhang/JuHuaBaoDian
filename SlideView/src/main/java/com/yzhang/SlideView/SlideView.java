package com.yzhang.SlideView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by yzhang on 2017/4/13.
 */

public class SlideView extends FrameLayout implements MyViewAnimator.onPageChangedListener{

    private float startEventX;
    private boolean changePic = true;

    private MyViewFlipper flipper;
    private LinearLayout dotLayout;
    private int[] imageRes;
    private int page_num = 0;

    private int FlipInterval;
    private int dot_blur;
    private int dot_focus;
    private int dot_margin;
    private int left_in;
    private int left_out;
    private int right_in;
    private int right_out;

    public SlideView(Context context) {
        this(context,null);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        flipper = (MyViewFlipper)findViewById(R.id.flipper);
        dotLayout = (LinearLayout)findViewById(R.id.dotLayout);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideView);

        FlipInterval = typedArray.getInt(R.styleable.SlideView_flip_interval,10000);
        dot_blur = typedArray.getResourceId(R.styleable.SlideView_dot_blur,R.drawable.dot_blur);
        dot_focus = typedArray.getResourceId(R.styleable.SlideView_dot_focus,R.drawable.dot_focus);
        dot_margin = typedArray.getDimensionPixelSize(R.styleable.SlideView_dot_margin,5);
        left_in = typedArray.getResourceId(R.styleable.SlideView_left_in,R.anim.left_in);
        left_out = typedArray.getResourceId(R.styleable.SlideView_left_out,R.anim.left_out);
        right_in = typedArray.getResourceId(R.styleable.SlideView_right_in,R.anim.right_in);
        right_out = typedArray.getResourceId(R.styleable.SlideView_right_out,R.anim.right_out);

        typedArray.recycle();

        flipper.setOnPageChangedListener(this);
    }

    public void init(int[] imageRes){
        this.imageRes = imageRes;
        flipper.removeAllViews();
        dotLayout.removeAllViews();
        for(int i = 0 ; i < imageRes.length ; i++){
            ImageView view = new ImageView(getContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(layoutParams);
            view.setImageResource(imageRes[i]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            flipper.addView(view);

            ImageView point = new ImageView(getContext());
            LinearLayout.LayoutParams pointLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pointLayoutParams.leftMargin = dot_margin;
            pointLayoutParams.rightMargin = dot_margin;
            point.setLayoutParams(pointLayoutParams);
            point.setImageResource(dot_blur);
            dotLayout.addView(point);
        }
        flipper.setInAnimation(getContext(), left_in);
        flipper.setOutAnimation(getContext(), left_out);
        flipper.setFlipInterval(FlipInterval);
        flipper.startFlipping();

        flipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startEventX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE://判断向左滑动还是向右滑动
                        if(!changePic){
                            return false;
                        }
                        if(startEventX - event.getX() > 100){
                            flipper.setInAnimation(getContext() , left_in);
                            flipper.setOutAnimation(getContext() , left_out);
                            changePic = false;
                            flipper.showPrevious();
                        }else if(event.getX() - startEventX > 100){
                            flipper.setInAnimation(getContext() , right_in);
                            flipper.setOutAnimation(getContext() , right_out);
                            changePic = false;
                            flipper.showNext();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        changePic = true;
                        break;
                }
                return true;
            }
        });

        ((ImageView)dotLayout.getChildAt(page_num)).setImageResource(dot_focus);
    }

    @Override
    public void onPageChanged(int i) {
        ((ImageView)dotLayout.getChildAt(page_num)).setImageResource(dot_blur);
        page_num = page_num + i;
        if (page_num == -1)page_num = imageRes.length-1;
        if (page_num == imageRes.length) page_num = 0;
        ((ImageView)dotLayout.getChildAt(page_num)).setImageResource(dot_focus);
    }
}
