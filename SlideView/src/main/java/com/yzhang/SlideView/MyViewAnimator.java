package com.yzhang.SlideView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * Created by yzhang on 2017/4/14.
 */

public class MyViewAnimator extends ViewAnimator {

    onPageChangedListener listener;

    public MyViewAnimator(Context context) {
        super(context);
    }

    public MyViewAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnPageChangedListener(onPageChangedListener listener){
        this.listener = listener;
    }

    @Override
    public void showNext(){
        if(listener != null)
            listener.onPageChanged(1);
        super.showNext();
    }

    @Override
    public void showPrevious(){
        if(listener != null)
            listener.onPageChanged(-1);
        super.showPrevious();
    }

    public interface onPageChangedListener{
        void onPageChanged(int i);
    }
}
