package com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Proxy;

/**
 * Created by yzhang on 2017/5/8.
 */

public class RealSubject implements Subject {
    public void request(){
        System.out.println("====RealSubject Request====");
    }
}
