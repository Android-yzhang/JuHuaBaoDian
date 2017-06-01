package com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yzhang on 2017/2/20.
 */

public class LocalService extends Service {

    private String TAG = getClass().getName();

    class MyBinder extends Binder {
        public LocalService getMyService() {
            return LocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "LocalService onBind");
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "LocalService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "LocalService onStart");
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "LocalService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "LocalService onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "LocalService onDestroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "LocalService onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onLowMemory() {
        Log.e(TAG, "LocalService onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(TAG, "LocalService onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e(TAG, "LocalService onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level) {
        /**
         * TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
         * TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。
         * TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
         * TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。
         */
        Log.e(TAG, "LocalService onTrimMemory : level = " + level);
        super.onTrimMemory(level);
    }
}
