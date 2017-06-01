package com.yzhang.juhuabaodian.Demos.WidgetModule.BottomNavigationView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.yzhang.juhuabaodian.R;

/**
 * Created by yzhang on 2017/5/2.
 */

public class BottomNavigationViewDemoActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    TextView mNavTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.botton_navigation_view_demo_activity);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_view);
        mNavTv = (TextView)findViewById(R.id.nav_tv);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_ui:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_data:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_service:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_net:
                        mNavTv.setText(item.getTitle());
                        break;
                    case R.id.bottom_nav_media:
                        mNavTv.setText(item.getTitle());
                        break;
                }
                return true;
            }
        });
    }
}
