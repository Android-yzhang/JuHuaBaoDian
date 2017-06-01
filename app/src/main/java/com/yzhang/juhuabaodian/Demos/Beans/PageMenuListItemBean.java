package com.yzhang.juhuabaodian.Demos.Beans;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by yzhang on 2017/2/13.
 */

public class PageMenuListItemBean implements Serializable {
    private static final long serialVersionUID = -3068108731466660644L;
    private String name;
    private String documentName;
    private Class activity;

    public PageMenuListItemBean(String name, String documentName, @NonNull Class activity) {
        this.name = name;
        this.documentName = documentName;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Class getActivity() {
        return activity;
    }
}
