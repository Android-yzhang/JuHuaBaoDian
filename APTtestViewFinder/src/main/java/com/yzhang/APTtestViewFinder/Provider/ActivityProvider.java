package com.yzhang.APTtestViewFinder.Provider;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by yzhang on 2017/4/24.
 */

public class ActivityProvider implements Provider {
    @Override
    public Context getContext(Object source) {
        return ((Activity) source);
    }

    @Override
    public View findView(Object source, int id) {
        return ((Activity) source).findViewById(id);
    }
}
