package com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Reflect.testClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yzhang on 2017/5/8.
 */

public class ReflectDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        scrollView.addView(textView);
        StringBuffer stringBuffer = new StringBuffer();

        /*Class*/
        /**
         * 可以拿到对象
         */
        testClass class1 = new testClass();
        Class clazz = class1.getClass();

        /**
         * 知道路径（包+className）com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Reflect.testClass
         */
        Class clazz1 = null;
        try {
            clazz1 = Class.forName("com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Reflect.testClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 加载指定的 Class 对象，参数 1 为要加载的类的完整路径，例如"com.simple.Student";
        // 参数 2 为是否要初始化该 Class 对象，参数 3 为指定加载该类的 ClassLoader.
        Class clazz2 = null;
        try {
            clazz2 = Class.forName ("com.yzhang.juhuabaodian.Demos.OSBaseModule.ReflectAndProxy.Reflect.testClass", true, getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*Constructor(构造器)*/
        try {
            Constructor constructor = clazz.getConstructor();
            Object object = constructor.newInstance();
            Log.e("Reflect" , object.toString());
            stringBuffer.append(object.toString()).append("\n");

            /*获得父类*/
            Class superClass = clazz.getSuperclass();
            Log.e("Reflect" , superClass.getName() + " , " + superClass.toString());
            stringBuffer.append(superClass.getName() + " , " + superClass.toString()).append("\n");
            /*获得接口*/
            Class[] Interfaces = clazz.getInterfaces();
            for(Class Interface : Interfaces){
                Log.e("Reflect" , Interface.getName() + " , " + Interface.toString());
                stringBuffer.append(Interface.getName() + " , " + Interface.toString()).append("\n");
            }
            /*Method （方法）*/
            Method[] methods = clazz.getMethods();//包含父类继承的方法，不含private方法
            for(Method method :methods){
                Log.e("Reflect" , method.getName() + " , " +method.toString());
                stringBuffer.append(method.getName() + " , " +method.toString()).append("\n");
            }
            Log.e(" " , "");
            Method[] declaredMethods = clazz.getDeclaredMethods();//只有自身方法，包含private方法
            for(Method method :declaredMethods){
                Log.e("Reflect" , method.getName() + " , " +method.toString());
                stringBuffer.append(method.getName() + " , " +method.toString()).append("\n");
                if(method.getName().equals("voidMethod")){
                    Log.e(" " , "");
                    String string = (String) method.invoke(object , "testString");
                    Log.e("Reflect" , string);
                    stringBuffer.append("\n").append(string).append("\n").append("\n");
                    Log.e(" " , "");
                }
            }

            Method method = clazz.getDeclaredMethod("privateMethod");
            method.setAccessible(true); // 反射私有方法需要抑制Java的访问控制检查
            method.invoke(object);

            Log.e(" " , "");
            stringBuffer.append("\n");
            Method[] superClassMethods = superClass.getDeclaredMethods();
            for(Method superMethod : superClassMethods){
                Log.e("Reflect" , superMethod.getName() + " , " +superMethod.toString());
                stringBuffer.append(superMethod.getName() + " , " +superMethod.toString()).append("\n");
            }
            Log.e(" " , "");
            stringBuffer.append("\n");
            /*Filed （参数）*/
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                Log.e("Reflect" , field.getName() + " , " +field.toString());
                stringBuffer.append(field.getName() + " , " +field.toString()).append("\n");
            }
            Field field = clazz.getDeclaredField("testString");
            field.setAccessible(true);
            Log.e(" " , "");
            Log.e("Reflect" , field.get(object).toString());
            stringBuffer.append("\n").append(field.get(object).toString()).append("\n").append("\n");
            Log.e(" " , "");
            field.set(object,"this is a test");
            Log.e("Reflect" , field.get(object).toString());
            stringBuffer.append(field.get(object).toString()).append("\n");

            /*Annotation （注释）*/
            Annotation[] methodAnnotations = method.getAnnotations();
            for(Annotation annotation : methodAnnotations){
                Log.e("Reflect" , annotation.toString());
                stringBuffer.append(annotation.toString()).append("\n");
            }
            Annotation[] fieldAnnotations = field.getAnnotations();
            for(Annotation annotation : fieldAnnotations){
                Log.e("Reflect" , annotation.toString());
                stringBuffer.append(annotation.toString()).append("\n");
            }

            if(clazz1 != null){
                Constructor constructor1 = clazz1.getConstructor(String.class);
                Object object1 = constructor1.newInstance("testString");
                Log.e("Reflect" , object1.toString());
            }
            if(clazz2 != null){
                Constructor constructor2 = clazz2.getConstructor(int.class,String.class);
                Object object2 = constructor2.newInstance(1,"testInteger and testString");
                Log.e("Reflect" , object2.toString());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        textView.setText(stringBuffer.toString());
        setContentView(scrollView);
        find();
    }

    private void find(){
        try {
            Class clazz = Class.forName("dalvik.system.DexPathList");
            Method[] methods = clazz.getMethods();
            for(Method method : methods){
                Log.e("find DexPathList" , method.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
