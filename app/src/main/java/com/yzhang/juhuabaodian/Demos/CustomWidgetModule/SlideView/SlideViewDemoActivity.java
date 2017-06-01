package com.yzhang.juhuabaodian.Demos.CustomWidgetModule.SlideView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yzhang.SlideView.SlideView;
import com.yzhang.juhuabaodian.R;

/**
 * Created by yzhang on 2017/4/13.
 */

public class SlideViewDemoActivity extends AppCompatActivity {

    SlideView slideView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_view_demo_activity);

        slideView = (SlideView) findViewById(R.id.slideView);
        slideView.init(new int[]{R.drawable.duoyun, R.drawable.duoyun});
    }
}
