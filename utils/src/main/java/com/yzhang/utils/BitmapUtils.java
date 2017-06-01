package com.yzhang.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.InputStream;

/**
 * Created by yzhang on 2017/2/23.
 */

public final class BitmapUtils {
    public static int calculateInSampleSize(InputStream inputStream, View view) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        int a = bitmap.getWidth() / view.getWidth();
        int b = bitmap.getHeight() / view.getHeight();
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
