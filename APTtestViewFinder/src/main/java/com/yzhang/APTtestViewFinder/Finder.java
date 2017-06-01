package com.yzhang.APTtestViewFinder;

import com.yzhang.APTtestViewFinder.Provider.Provider;

/**
 * Created by yzhang on 2017/4/24.
 */

public interface Finder<T> {
    void inject(T host, Object source, Provider provider);
}
