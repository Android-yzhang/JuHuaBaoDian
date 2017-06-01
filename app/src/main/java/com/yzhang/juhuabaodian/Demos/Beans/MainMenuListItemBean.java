package com.yzhang.juhuabaodian.Demos.Beans;

import android.support.v4.app.Fragment;

/**
 * Created by yzhang on 2017/2/13.
 */

public class MainMenuListItemBean {
    private String name;
    private Fragment page;

    public MainMenuListItemBean(String name, Fragment page) {
        this.name = name;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public Fragment getPage() {
        return page;
    }
}
