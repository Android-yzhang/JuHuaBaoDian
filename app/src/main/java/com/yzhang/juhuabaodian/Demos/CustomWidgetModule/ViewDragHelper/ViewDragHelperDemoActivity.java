package com.yzhang.juhuabaodian.Demos.CustomWidgetModule.ViewDragHelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yzhang on 2017/4/18.
 */

public class ViewDragHelperDemoActivity extends AppCompatActivity {

    VDHLayout vdhLayout;

    TextView textView1, textView2, textView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vdhLayout = new VDHLayout(this);
        vdhLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(vdhLayout);

        textView1 = new TextView(this);
        textView1.setWidth(200);
        textView1.setHeight(200);
        textView1.setText("textView1");
        vdhLayout.addView(textView1);

        textView2 = new TextView(this);
        textView2.setWidth(200);
        textView2.setHeight(200);
        textView2.setText("textView2");
        vdhLayout.addView(textView2);

        textView3 = new TextView(this);
        textView3.setWidth(200);
        textView3.setHeight(200);
        textView3.setText("textView3");
        vdhLayout.addView(textView3);
    }
}
