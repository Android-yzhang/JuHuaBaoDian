package com.yzhang.juhuabaodian.Demos.OtherModule.RxJava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by yzhang on 2017/3/15.
 */

public class RxJavaDemoActivity extends AppCompatActivity {

    String tag = "RxJava";

    /**
     * 订阅(Subscribe)
     * Observable (可观察者，即被观察者)
     * Observer (观察者)
     * Scheduler （调度器）
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hi，Weavey！");  //发送数据"Hi，Weavey！"
                subscriber.onNext("Hi，Weavey！");
                subscriber.onError(new Throwable("There is a Error"));//与下面的onCompleted互斥，调用onError后不会调用onCompleted
                subscriber.onCompleted();
            }
        });

        Observer<String> receiver = new Observer<String>() {

            @Override
            public void onCompleted() {
                //数据接收完成时调用
                Log.e(tag, "onCompleted 数据接收完成");
            }

            @Override
            public void onError(Throwable e) {
                //发生错误调用
                Log.e(tag, e.toString());
            }

            @Override
            public void onNext(String s) {
                //正常接收数据调用
                Log.e(tag, s);  //将接收到来自sender的问候"Hi，Weavey！"
            }
        };

        sender.subscribe(receiver);


        String[] names = {"张三", "李四", "王五"};
        Observable.from(names) //from 传入一个数组或Iterable，按照相应的数量重复调用
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.e(tag, name);
                    }
                });

        /**
         *
         */
        Observable.just(1, 2, 3, 4)//just会转化成from
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.e(tag, "number:" + number);
                    }
                });
    }
}
