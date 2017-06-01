package com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by yzhang on 2017/2/20.
 */

public class RemoteService extends Service {

    private String TAG = getClass().getName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "RemoteService onBind");
        return new MyRemoteServiceImpl();
    }

    private class MyRemoteServiceImpl extends IRemoteServiceInterface.Stub {

        @Override
        public String getClassName() throws RemoteException {
            return TAG;
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "RemoteService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "RemoteService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "RemoteService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "RemoteService onDestroy");
        super.onDestroy();
    }
}
