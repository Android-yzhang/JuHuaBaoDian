package com.yzhang.juhuabaodian.Demos.OSBaseModule.ContextAndOthers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by yzhang on 2017/2/17.
 */

public class StaticBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("static_broadcast_receiver_test_action")) {
            Toast.makeText(context, "静态广播收到信息：" + intent.getStringExtra("content"), Toast.LENGTH_LONG).show();
        }
    }
}
