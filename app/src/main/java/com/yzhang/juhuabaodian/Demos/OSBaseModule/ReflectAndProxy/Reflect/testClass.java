package com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Reflect;

import android.util.Log;

/**
 * Created by yzhang on 2017/5/12.
 */

public class testClass implements TestInterface{

    public int testInteger;
    @FieldAnnotation
    private String testString = "";

    public testClass() {
        testString = "";
    }

    public testClass(String testString) {
        this.testString = testString;
    }

    public testClass(int testInteger , String testString){
        this.testInteger = testInteger;
        this.testString = testString;
    }

    public void publicMethod(){
        Log.e("Reflect","publicMethod");
    }

    @MethodAnnotation
    private void privateMethod(){
        Log.e("Reflect","privateMethod");
    }

    protected void protectedMethod(){
        Log.e("Reflect","protectedMethod");
    }

    @MethodAnnotation
    String voidMethod(String str){
        Log.e("Reflect","voidMethod");
        return testString + " , " + str;
    }

    @Override
    public String toString() {
        return "testInteger : " + testInteger + "\ttestString : " + testString;
    }
}
