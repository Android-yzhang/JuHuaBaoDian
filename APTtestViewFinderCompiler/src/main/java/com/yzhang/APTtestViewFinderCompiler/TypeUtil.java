package com.yzhang.APTtestViewFinderCompiler;

import com.squareup.javapoet.ClassName;

/**
 * Created by yzhang on 2017/4/24.
 */

public class TypeUtil {
    public static final ClassName ANDROID_VIEW = ClassName.get("android.view", "View");
    public static final ClassName ANDROID_ON_CLICK_LISTENER = ClassName.get("android.view", "View", "OnClickListener");
    public static final ClassName FINDER = ClassName.get("com.yzhang.APTtestViewFinder", "Finder");
    public static final ClassName PROVIDER = ClassName.get("com.yzhang.APTtestViewFinder.Provider", "Provider");
}
