package com.yzhang.juhuabaodian.Demos.OtherModule.APT;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yzhang.APTtestViewFinder.FindViewByID;
import com.yzhang.APTtestViewFinder.OnClick;
import com.yzhang.APTtestViewFinder.ViewFinder;
import com.yzhang.juhuabaodian.R;

/**
 * Created by yzhang on 2017/4/24.
 */

public class APTDemoActivity extends AppCompatActivity {
    @FindViewByID(R.id.text)
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apt_demo_activity);
        ViewFinder.inject(this);

        text.setText("This is a text");
    }

    @OnClick(R.id.text)
    void onClick() {
        text.setText("text on click");
    }
}
