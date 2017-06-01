package com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yzhang.juhuabaodian.R;

/**
 * Created by yzhang on 2017/2/16.
 */

public class ContextAndOthersMainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = getClass().getName();

    LocalServiceConnection localServiceConnection;
    LocalService localService;
    RemoteServiceConnection remoteServiceConnection;
    IRemoteServiceInterface remoteService;

    Button send_static_broadcast, send_dynamic_broadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "Activity Create");
        setContentView(R.layout.context_and_others_main_activity);

        /* 服务部分 */
        localServiceConnection = new LocalServiceConnection();
        startService(new Intent(this, LocalService.class));
        bindService(new Intent(this, LocalService.class), localServiceConnection, Context.BIND_AUTO_CREATE);

        /**
         * 因为还是在同一个应用里调用，所以startService的Intent设置的仍然为RemoteService
         * 如果再另一个应用中调用RemoteService，则隐式调用，因为在本地找不到RemoteService.class这个类
         * 写成Intent remoteService = new Intent();
         * remoteService.setAction("com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers.RemoteService");
         * 定义的service的action应该和AndroidManifest.xml中定义的Action一致
         */
        remoteServiceConnection = new RemoteServiceConnection();
        Intent remoteService = new Intent();
        remoteService.setAction("com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers.RemoteService");
        startService(remoteService);
        bindService(remoteService, remoteServiceConnection, Context.BIND_AUTO_CREATE);

        /* 广播部分 */
        send_static_broadcast = (Button) findViewById(R.id.send_static_broadcast);
        send_dynamic_broadcast = (Button) findViewById(R.id.send_dynamic_broadcast);
        send_static_broadcast.setOnClickListener(this);
        send_dynamic_broadcast.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "Activity Start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "Activity Restart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "Activity Resume");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dynamic_broadcast_receiver_test_action");
        registerReceiver(DynamicBroadCastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Activity Pause");
        unregisterReceiver(DynamicBroadCastReceiver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "Activity Stop");
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, LocalService.class));
        unbindService(localServiceConnection);
        /**
         * 因为还是在同一个应用里调用，所以stopService的Intent设置的仍然为RemoteService
         * 如果再另一个应用中调用RemoteService，则隐式调用，因为在本地找不到RemoteService.class这个类
         * 写成Intent remoteService = new Intent();
         * remoteService.setAction("com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers.RemoteService");
         * 定义的service的action应该和AndroidManifest.xml中定义的Action一致
         */
        Intent remoteService = new Intent();
        remoteService.setAction("com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers.RemoteService");
        stopService(remoteService);
        unbindService(remoteServiceConnection);

        Log.e(TAG, "Activity Destroy");
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "Activity RestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "Activity SaveInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "Activity ConfigurationChanged");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_static_broadcast: {
                Intent intent = new Intent("static_broadcast_receiver_test_action");
                intent.putExtra("content", "我发送了一个广播");
                sendBroadcast(intent);
                break;
            }
            case R.id.send_dynamic_broadcast: {
                Intent intent = new Intent("dynamic_broadcast_receiver_test_action");
                intent.putExtra("content", "我发送了一个广播");
                sendBroadcast(intent);
                break;
            }
        }
    }

    public class LocalServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            localService = ((LocalService.MyBinder) service).getMyService();
            Log.e(TAG, "Activity onServiceConnected : " + name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "Activity onServiceDisconnected");

        }
    }

    private class RemoteServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteService = IRemoteServiceInterface.Stub.asInterface(service);
            try {
                Toast.makeText(ContextAndOthersMainActivity.this, remoteService.getClassName(), Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "Activity onServiceConnected : " + name);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "Activity onServiceDisconnected");

        }
    }

    private BroadcastReceiver DynamicBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("dynamic_broadcast_receiver_test_action")) {
                Toast.makeText(context, "动态广播收到信息：" + intent.getStringExtra("content"), Toast.LENGTH_LONG).show();
            }
        }
    };
}
