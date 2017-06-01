package com.yzhang.juhuabaodian;

import com.yzhang.juhuabaodian.Demos.OtherModule.Dagger2.Dagger2DemoActivity;

import dagger.Component;

/**
 * Created by yzhang on 2017/3/24.
 */

@Component(modules = {AppModule.class/*, GithubApiModule.class*/})
public interface AppComponent {
    void inject(Dagger2DemoActivity activity);
}
