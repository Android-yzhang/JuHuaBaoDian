package com.yzhang.APTtestViewFinder;

import android.app.Activity;
import android.view.View;

import com.yzhang.APTtestViewFinder.Provider.ActivityProvider;
import com.yzhang.APTtestViewFinder.Provider.Provider;
import com.yzhang.APTtestViewFinder.Provider.ViewProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yzhang on 2017/4/24.
 */

public class ViewFinder {

    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    private static final ViewProvider PROVIDER_VIEW = new ViewProvider();

    private static final Map<String, Finder> FINDER_MAP = new HashMap<>();


    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }
    public static void inject(View view) {
        // for view
        inject(view, view);
    }
    public static void inject(Object host, View view) {
        // for fragment
        inject(host, view, PROVIDER_VIEW);
    }
    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            Finder finder = FINDER_MAP.get(className);
            if (finder == null) {
                Class<?> finderClass = Class.forName(className + "$$Finder");
                finder = (Finder) finderClass.newInstance();
                FINDER_MAP.put(className, finder);
            }
            finder.inject(host, source, provider);
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}
