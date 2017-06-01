package com.yzhang.juhuabaodian;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yzhang on 2017/3/24.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }
}
