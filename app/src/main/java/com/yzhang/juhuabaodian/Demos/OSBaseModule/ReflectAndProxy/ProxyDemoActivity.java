package com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Proxy.ProxyHandler;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Proxy.RealSubject;
import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Proxy.Subject;

import java.lang.reflect.Proxy;

/**
 * Created by yzhang on 2017/5/8.
 */

public class ProxyDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RealSubject realSubject = new RealSubject();    //1.创建委托对象
        ProxyHandler handler = new ProxyHandler(realSubject);    //2.创建调用处理器对象
        Subject proxySubject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(), handler);    //3.动态生成代理对象
        proxySubject.request();    //4.通过代理对象调用方法
    }
}
