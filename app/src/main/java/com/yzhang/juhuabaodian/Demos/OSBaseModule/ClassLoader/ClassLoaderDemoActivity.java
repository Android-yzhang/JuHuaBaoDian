package com.yzhang.juhuabaodian.Demos.OSBaseModule.ClassLoader;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yzhang.ClassLoaderTest.IShowToast;

import java.io.File;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by yzhang on 2017/5/8.
 */

public class ClassLoaderDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClassLoader loader = getClassLoader();
        while (loader != null) {
            System.out.println("ClassLoader:"+loader.toString());
            loader = loader.getParent();
        }
        File dexOutputDir = getDir("dex1", 0);
        String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator + "classes.jar";
        DexClassLoader loader1 = new DexClassLoader(dexPath,
                    dexOutputDir.getAbsolutePath(),
                    null, getClassLoader());
        try {
            Class clz = loader1.loadClass("com.yzhang.ClassLoaderTest.ShowToastImpl");
            IShowToast impl = (IShowToast) clz.newInstance();
            impl.showToast(this);
        } catch (Exception e) {
            Log.d("TEST111", "error happened", e);
        }
    }
    Class a;
}
