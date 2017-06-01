package com.yzhang.juhuabaodian;

import android.app.Application;

/**
 * Created by yzhang on 2017/3/24.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        setupComponent();
    }

    private void setupComponent() {
//        appComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .build();
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
