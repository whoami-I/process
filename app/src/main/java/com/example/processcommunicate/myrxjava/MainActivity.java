package com.example.processcommunicate.myrxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


import com.example.processcommunicate.R;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rxjava);

        Log.e(TAG, "main thread" + Thread.currentThread().getId());
        obs();


    }

    private void obs() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(Emitter<Integer> emitter) {
                emitter.onNext(2);
                Log.e(TAG, "subscribe thread:" + Thread.currentThread().getId());
            }
        });


        Observable<String> observable1 = observable.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                Log.e(TAG, "apply thread:" + Thread.currentThread().getId());
                return "this is " + integer.intValue();
            }
        });

        observable.subscribeOn(Schedulers.NEWTHREAD).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer s) {
//                Log.e(TAG, "onNext");
//                Log.e(TAG, "s --> " + s);
                Log.e(TAG, "onNext thread:" + Thread.currentThread().getId());

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });


    }


}
