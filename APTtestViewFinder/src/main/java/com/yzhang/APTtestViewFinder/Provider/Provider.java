package com.yzhang.APTtestViewFinder.Provider;

import android.content.Context;
import android.view.View;

/**
 * Created by yzhang on 2017/4/24.
 */

public interface Provider {
    Context getContext(Object source);
    View findView(Object source, int id);
}
