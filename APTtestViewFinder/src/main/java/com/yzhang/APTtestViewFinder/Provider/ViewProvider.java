package com.yzhang.APTtestViewFinder.Provider;

import android.content.Context;
import android.view.View;

/**
 * Created by yzhang on 2017/4/24.
 */

public class ViewProvider implements Provider {
    @Override
    public Context getContext(Object source) {
        return ((View) source).getContext();
    }

    @Override
    public View findView(Object source, int id) {
        return ((View) source).findViewById(id);
    }
}
