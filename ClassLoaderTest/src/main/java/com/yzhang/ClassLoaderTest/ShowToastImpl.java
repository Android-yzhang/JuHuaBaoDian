package com.yzhang.ClassLoaderTest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yzhang on 2017/5/9.
 */

public class ShowToastImpl implements IShowToast {
    @Override
    public void showToast(Context context) {
        Toast.makeText(context, "我来自另一个dex文件", Toast.LENGTH_LONG).show();
    }
}
