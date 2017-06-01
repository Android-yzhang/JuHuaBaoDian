package com.yzhang.juhuabaodian.Demos.WidgetModule.ViewAndViewGroup;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.hopen.JewelTD.JewelTDMainActivity;
import com.yzhang.juhuabaodian.R;

/**
 * Created by yzhang on 2017/3/1.
 */

public class ViewAndViewGroupMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_and_viewgroup_main_activity);
    }

    public void testJewelTD(View view) {
        startActivity(new Intent(this, JewelTDMainActivity.class));
    }
}
